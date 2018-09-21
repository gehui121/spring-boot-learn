<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WebSocket广播式</title>
	<script src="/stomp.min.js"></script>
	<script src="/sockjs.min.js"></script>
	<script src="/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		var stompClient = null;
		//设置链接状态控制显示隐藏
		function setConnected(connected){
			$("#connect").attr("disabled",connected);
			$("#disconnect").attr("disabled",!connected);
			if(!connected){
				$("#inputDiv").hide();
			}else{
				$("#inputDiv").show();
			}
			$("#response").html("");			
		};
		//链接
		//可以看到我们通过SockJs来注册了endpoine，并且通过客户端开启通过连接，
		//连接成功后调用setConnected方法来修改界面的元素显示隐藏
		function connect(){
			var socket = new SockJS("endpointWisely");
			stompClient = Stomp.over(socket);
			stompClient.connect({},function(frame){
				setConnected(true);
				console.log("connected"+frame);
				stompClient.subscribe("/topic/getResponse",function(response){
					console.info(response);
					showResponse(JSON.parse(response.body).responseMessage);
				})
			})
		};
		//断开链接
		function disconnect(){
			if(stompClient != null){
				stompClient.disconnect();
			}
			setConnected(false);
			console.log("disconnected!");
		};
		
		//发送名称到后台
		function seneName(){
			var name = $("#name").val();
			stompClient.send("/welcome",{},JSON.stringify({'name':name}));
		}
		
		//通过注册/topic/getReponse路径来回去服务端向浏览器端的请求数据内容，并且调用showResponse方法显示在页面中
		//显示socket返回消息内容
		function showResponse(message){
			$("#response").html(message);
		}
	</script>
</head>
<body onload="disconnect()">
	<button id="connect" onclick="connect()">链接</button>
	<button id="disconnect" onclick="disconnect()">断开链接</button>
	<div id="inputDiv">
		输入名称<input type="text" id="name"><br/>
		<button id="sendName" onclick="seneName()">发送</button><br/>
		<p id="response"></p>
	</div>
</body>
</html>