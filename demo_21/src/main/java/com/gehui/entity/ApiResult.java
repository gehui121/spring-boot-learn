package com.gehui.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;

public class ApiResult implements Serializable{
	//禁止new创建对象
	private ApiResult() {};
	
	/**
	 * 统一创建APIResult对象，方便后期扩展
	 */
	public static ApiResult newInstance() {
		return new ApiResult();
	}
	
	//消息提示
	private String msg;
	//状态信息
	private boolean flag=true;
	//返回结构
	private Object result;
	//查询出的结构总数
	private int rows;
	//需要跳转的路径
	private String jumpUrl;
	//借口响应时间毫秒单位
	private Long time;
	//返回类型  E失败  S成功
	private String type;
	//返回状态码 AAAAAA成功000000失败
	private String code;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ApiResult [msg=" + msg + ", flag=" + flag + ", result=" + result + ", rows=" + rows + ", jumpUrl="
				+ jumpUrl + ", time=" + time + "]";
	}
	
	
	
	
	
	
}
