package com.gehui.controller;

import com.gehui.entity.DemoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/8/9 16:09.
 **/
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/validator")
    public String validator(@Valid DemoEntity entity, BindingResult result){
        if (result.hasErrors()){
            StringBuffer msg = new StringBuffer();
            //获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            //获取本地local zh_CN
            Locale currentLocal = LocaleContextHolder.getLocale();
            logger.info("本地语言是：" +currentLocal);
            //遍历错误字段获取错误信息
            for (FieldError fieldError : fieldErrors){
                //获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, currentLocal);
                //添加到错误消息集合中
                logger.info("错误信息中的字段："+fieldError.getField());
                msg.append(fieldError.getField()+":"+errorMessage+",");
            }
            return msg.toString();
        }
        return "验证通过：" + "名称：" + entity.getName()+"年龄" + entity.getAge() + "邮箱是："+entity.getMail();
    }

}
