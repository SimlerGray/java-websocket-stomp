package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by apple on 17/8/14.
 */
@Configuration
public class WebSoketConfig{


	@Bean
	public ServerEndpointExporter serverEndpointExporter (){
		return new ServerEndpointExporter();
	}

//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) { //endPoint 注册协议节点,并映射指定的URl
//
//		//注册一个名字为"endpointChat" 的endpoint,并指定 SockJS协议。   点对点-用
//		registry.addEndpoint("/websocket").withSockJS();
//	}
//
//
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(message broker)
//		//点对点式增加一个/queue 消息代理
//		registry.enableSimpleBroker("/queue","/topic");
//
//	}
}
