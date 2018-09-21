package com.gehui.entity;
/**
 * 用浏览器向服务器发送消息参数
 * @author pactera
 *
 */
public class WiselyMessage {

	private String name;

	public WiselyMessage(String name) {
		super();
		this.name = name;
	}

	public WiselyMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "WiselyMessage [name=" + name + "]";
	}
	
	
}
