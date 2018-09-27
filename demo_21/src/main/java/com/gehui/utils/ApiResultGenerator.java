package com.gehui.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gehui.entity.ApiResult;

/**
 * 创建一个ApiResultGenerator用来生成ApiResult不同状态的返回内容（成功、失败）。
 * @author pactera
 *该类是用来创建成功、失败返回JSON的工具类
 */
public class ApiResultGenerator {

	public static ApiResult result(String msg, boolean flag, Object result, int rows, String jumpUrl, String type, String code, Throwable throwable) {
		//创建返回对象
		ApiResult apiResult = ApiResult.newInstance();
		apiResult.setFlag(flag);
		apiResult.setJumpUrl(jumpUrl);
		apiResult.setMsg(msg);
		apiResult.setResult(result);
		apiResult.setRows(rows);
		apiResult.setTime(System.currentTimeMillis());
		apiResult.setCode(code);
		apiResult.setType(type);
		return apiResult;
	}
	/**
	 * 返回执行成功视图方法
	 * @param result 执行成功后返回的内容
	 * @return
	 */
	public static ApiResult successResult(Object result) {
		//rows 默认为0
		int rows = 0;
		//如果是集合
		if (result instanceof List) {
			//获取总数量
			rows = ((List) result).size();
		}
		return result("", true, result, rows, "", "S", "AAAAAA", null);
	}
	
	/**
	 * 执行失败后返回视图方法
	 * @param msg 执行失败后的错误消息内容
	 * @param throwable
	 * @return
	 */
	public static ApiResult errorResult(String msg, Throwable throwable) {
		return result(msg, false, "", 0, "", "E", "000000", throwable);
	}
	
}
