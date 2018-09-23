package com.gehui.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gehui.entity.ApiResult;
import com.gehui.utils.ApiResultGenerator;

/**
 * 统一异常处理具体controller类不需要进行单独的异常处理，统一由该类进行处理
 * @author pactera
 *
 */
//@ControllerAdvice注解是用来配置控制器通知的，我们可以配置过滤拦截具体一种或者多种类型的注解，添加annotations属性即可
@ControllerAdvice(annotations=RestController.class)
@ResponseBody
public class RestExceptionHandler {
	/**
	 * 默认统一异常处理方法
	 * @param e 默认异常对象
	 * @return
	 */
	@ExceptionHandler//注解用来配置需要拦截的异常类型，默认是全局类型
	@ResponseStatus//注解用于配置遇到该异常后返回数据时的StatusCode的值，我们这里默认使用值500。
	public ApiResult runtimeExceptionHandler(Exception e) {
		return ApiResultGenerator.errorResult(e.getMessage(), e);
	}



}
