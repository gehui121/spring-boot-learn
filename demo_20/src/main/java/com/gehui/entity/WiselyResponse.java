package com.gehui.entity;
/**
 * 服务端广播通知浏览器的实体类型
 * @author pactera
 *
 */
public class WiselyResponse {

	private String responseMessage;

	public WiselyResponse(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}

	public WiselyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "WiselyResponse [responseMessage=" + responseMessage + "]";
	}
	
	
}
