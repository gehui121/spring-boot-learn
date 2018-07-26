package com.gehui.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gehui.entity.LoggerEntity;
import com.gehui.jpa.LoggerJPA;
import com.gehui.utils.LoggerUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/26 11:13.
 * 请求日志几乎是所有大型企业级项目的必要的模块，请求日志对于我们来说后期在项目运行上线一段时间用于排除异常、
 * 请求分流处理、限制流量等。请求日志一般都会记录请求参数、请求地址、请求状态（Status Code）、SessionId、
 * 请求方法方式（Method）、请求时间、客户端IP地址、请求返回内容、耗时等等。如果你得系统还有其他个性化的配置，也可以完成记录。
 *
 **/
public class LoggerInterceptor implements HandlerInterceptor {

    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";


    /**
     * 创建LoggerEntity实体，并记录一些必要参数后将实体写入到当前的请求中
     * 进入SpringMvc的controller之前开始记录日志实体
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //创建日志实体
        LoggerEntity loggerEntity = new LoggerEntity();
        //获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        //请求路径
        String uri = request.getRequestURI();
        //获取请求参数信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //json解析视图
        String paramData = JSON.toJSONString(parameterMap,
                SerializerFeature.DisableCircularReferenceDetect, //消除对同一对象循环引用的问题，默认为false
                SerializerFeature.WriteMapNullValue );//是否输出值为null的字段,默认为false

        //设置客户端ip
        loggerEntity.setClientIp(LoggerUtils.getCliectIp(request));
        //设置请求方法
        loggerEntity.setMethod(request.getMethod());
        //设置请求类型;普通类型或者ajax请求
        loggerEntity.setType(LoggerUtils.getRequestType(request));
        //设置请求参数内容json字符串
        loggerEntity.setParamData(paramData);
        //设置请求地址
        loggerEntity.setClientUri(uri);
        //设置sesisonid
        loggerEntity.setSessionId(sessionId);
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
        //设置请求实体到request中，方便afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY,loggerEntity);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //获取请求开始时间
        Long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求实体
        LoggerEntity loggerEntity = (LoggerEntity)request.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime-time)+""));
        //设置返回时间
        loggerEntity.setReturnTime(currentTime+"");
        //设置返回错误码
        loggerEntity.setHttpStatusCode(status+"");
        //设置返回值
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        //执行将日志写入数据库
        LoggerJPA loggerDao = getDAO(LoggerJPA.class, request);
        loggerDao.save(loggerEntity);


    }

    /**
     * 根据传入的类型获取spring管理的对应dao
     * WebApplicationContextUtils这个工具类可以通过HttpServletRequest请求对象的上下文（ServletContext）获取spring管理的Bean，
     *
     * @param calzz   类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
    private <T> T getDAO(Class<T> calzz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(calzz);
    }
}
