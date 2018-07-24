package com.gehui;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24 17:05.
 * 实际上@restController中的controller本身返回的就是json字符串，SpringBoot中内置默认的jackjson
 * 处理中文乱码问题在properties配置文件中配置即可
 **/
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {
    /**
     * 修改自定义消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        //创建fastjson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                //消除对同一对象的循环引用的问题，默认为false，防止死循环
                SerializerFeature.DisableCircularReferenceDetect,
                //为null的字段返回并显示null
                // SerializerFeature.WriteMapNullValue
                //为null的字段返回并显示""空字符串
                SerializerFeature.WriteNullStringAsEmpty
        );
        //处理中文乱码问题 如果使用这种办法的话 WriteNullStringAsEmpty这些就失效了
//        List<MediaType> fastMediaType = new ArrayList<>();
//        fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaType);
//        fastConverter.setDefaultCharset(Charset.forName("utf-8"));不能解决乱码问题
//        fastJsonConfig.setCharset(Charset.forName("UTF-8"));不能解决乱码问题
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }

}
