package com.gehui.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.gehui.entity.WiselyMessage;
import com.gehui.entity.WiselyResponse;

@Controller
public class WebSocketController {
	//@MessageMapping注解开启WebSocket的访问地址映射,类似@RequestMapping
	@MessageMapping("/welcome")
	//当服务端有消息存在时，会对订阅@SendTo中路径的浏览器发送请求
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws InterruptedException {
		//等待3秒返回内容
		Thread.sleep(3000);
		return new WiselyResponse("欢迎使用websocket进行消息传送"+message.getName());
	}

}
