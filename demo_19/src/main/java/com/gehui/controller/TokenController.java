package com.gehui.controller;

import com.gehui.entity.TokenEntity;
import com.gehui.entity.TokenResult;
import com.gehui.entity.UserEntity;
import com.gehui.entity.jpa.TokenJPA;
import com.gehui.entity.jpa.UserJPA;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/12 14:40.
 **/
@RestController
@RequestMapping(value = "/jwt")
public class TokenController {

    @Autowired
    private TokenJPA tokenJPA;

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.POST})
    public TokenResult token(
            @RequestParam String appId,
            @RequestParam String appSecret) {

        Logger logger = LoggerFactory.getLogger(TokenController.class);
        TokenResult tokenResult = new TokenResult();
        //appId is null
        if (appId == null || appId.trim() == "") {
            tokenResult.setFlag(false);
            tokenResult.setMsg("appId is not found!");
        } else if (appSecret == null || appSecret.trim() == "") {
            tokenResult.setFlag(false);
            tokenResult.setMsg("appSecret is not found!");
        } else {
            //根据appId查询客户实体
            Optional<UserEntity> userDbInfo = userJPA.findOne(new Specification<UserEntity>() {
                @Override
                public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("appId"), appId));
                    return null;
                }
            });
            //如果值存在则方法会返回true，否则返回 false。
            String secretDB = new String(userDbInfo.get().getAppSectet());
            logger.info("查询数据库获取到的密码是："+ secretDB);
            String secret = appSecret.replace(" ", "+");

            logger.info("页面传入的密码经过替换后是：" + secret);
            logger.info("页面传入的密码是：" + appSecret);
            System.out.print("判断替换前后是否相等"+secret.equals(appSecret));
            logger.info("判断两个密码是否相等"+secret.equals(secretDB));
            System.out.print(secret.equals(secretDB));
            boolean flag = new String(userDbInfo.get().getAppSectet()).equals(appSecret.replace(" ", "+"));
            if (!userDbInfo.isPresent()) {
                tokenResult.setFlag(false);
                tokenResult.setMsg("appId" + appId + ",is not found!");
            } else if (!flag) {
                tokenResult.setFlag(false);
                tokenResult.setMsg("appSecret is not effective!");
            } else {
                //检测数据库是否存在该appId的token值
                Optional<TokenEntity> tokenDbInfo = tokenJPA.findOne(new Specification<TokenEntity>() {
                    @Override
                    public Predicate toPredicate(Root<TokenEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        criteriaQuery.where(criteriaBuilder.equal(root.get("appId"), appId));
                        return null;
                    }
                });
                //返回token值
                String tokenStr = null;
                //tokenDbInfo== null --》生成newToken-》保存数据库-》写入内存-》返回newToken
                if (!tokenDbInfo.isPresent()) {
                    //生成jwttoken
                    tokenStr = createNewToken(appId);
                    //将token保存到数据库
                    tokenDbInfo.get().setAppId(userDbInfo.get().getAppId());
                    tokenDbInfo.get().setBindTime(String.valueOf(System.currentTimeMillis()));
                    tokenDbInfo.get().setToken(tokenStr.getBytes());
                    //将token保存到数据库
                    tokenJPA.save(tokenDbInfo.get());
                }
                //tokenDbInfo != null ->验证是否超时
                //不超时 -》 直接返回 dbtoken
                //超时-》生成newToken-》更新DBToken-》更新内存Token-》返回newToken
                else {
                    //判断数据库中token是否过期，如果没有过期不需要更新，直接返回数据库中的token
                    //数据库中生成时间
                    Long dbBuindTime = Long.valueOf(tokenDbInfo.get().getBindTime());
                    //当前时间
                    long currentTime = System.currentTimeMillis();
                    //如果当前时间-数据库中生成的时间 <7200 证明可以正常使用
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuindTime);
                    if (seconds > 0 && seconds < 7200) {
                        tokenStr = new String(tokenDbInfo.get().getToken());
                    }
                    //超时
                    else {
                        //生成newToken
                        tokenStr = createNewToken(appId);
                        //更新token
                        tokenDbInfo.get().setToken(tokenStr.getBytes());
                        tokenDbInfo.get().setBindTime(String.valueOf(System.currentTimeMillis()));
                        tokenDbInfo.get().setAppId(appId);
                        tokenJPA.save(tokenDbInfo.get());
                    }
                }
                tokenResult.setToken(tokenStr);
            }
        }
        return tokenResult;
    }

    /**
     * 生成jwttoken
     *
     * @param appId
     * @return
     */
    private String createNewToken(String appId) {
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime() + 7200000);
        return Jwts
                .builder()
                .setSubject(appId)
                .setIssuedAt(now)
                .setIssuer("Online YAuth Builder")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "HengYuAuthv1.0.0")
                .compact();
    }
}
