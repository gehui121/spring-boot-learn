package com.gehui.interceptor;

import com.gehui.entity.TokenEntity;
import com.gehui.entity.jpa.TokenJPA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.Optional;

/**
 * Created by Administrator on 2018/9/12 17:44.
 * 我们在拦截器中需要验证头信息，Token的值是否存在，Subject用户是否存在等
 **/
public class JwtTokenInterceptor implements HandlerInterceptor {

    /**
     * 请求之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.print("spring  ..................");
        //自动排除生成token路径，并且如果是options请求是cors跨域预请求，设置allow对应头信息
        if (request.getRequestURI().equals("/token") || RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        //其他请求获取头信息
        final String authHeader = request.getHeader("X-YAuth-Token");
        try {
            //如果没有header信息
            if (authHeader == null || "".equals(authHeader.trim())) {
                throw new SignatureException("not found X-YAuth-Token");
            }
            //获取jwt实体对象接口实例
            Claims claims = Jwts.parser().setSigningKey("HengYuAuthv1.0.0").parseClaimsJws(authHeader).getBody();
            //从数库中获取token
            Optional<TokenEntity> tokenDbEntity = getDAO(TokenJPA.class, request).findOne(new Specification<TokenEntity>() {
                @Override
                public Predicate toPredicate(Root<TokenEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("appId"), claims.getSubject()));
                    return null;
                }
            });
            //数据库中的token值
            String tokenVal = new String(tokenDbEntity.get().getToken());
            //如果内存中不存在，提示客户获取token
            if (tokenVal == null || "".equals(tokenVal.trim())) {
                throw new SignatureException("not found token info, please got token agin!");
            }

            //判断内存中的token与客户端传入的token是否一致
            if (!tokenVal.equals(authHeader)) {
                throw new SignatureException("not found token info, please got token agin!");
            }
            //验证异常处理
        } catch (SignatureException | ExpiredJwtException e) {
            //输出对象
            response.getWriter().write("need refresh token");
            return false;
        }
        return true;
    }

    /**
     * 根据class获取对象
     *
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    private <T> T getDAO(Class<T> clazz, HttpServletRequest request) {

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.print("spring  ..................");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.print("spring  hou..................");
    }


}
