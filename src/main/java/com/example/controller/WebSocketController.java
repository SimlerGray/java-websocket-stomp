package com.example.controller;


import com.example.config.SocketSessionRegistry;
import com.example.entity.CommonEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 17/8/21.
 */
@Controller
public class WebSocketController {

//	/**session操作类*/
	@Autowired
SocketSessionRegistry webAgentSessionRegistry;
	/**消息发送工具*/
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/send_message")
	public void say(CommonEntity message)throws Exception {
		Map<String,String> params = new HashMap(1);
		params.put("msg","请等待");
		String sessionId= StringUtils.join(webAgentSessionRegistry.getSessionIds(message.getUser_Uuid()).toArray(),";");
		template.convertAndSendToUser(sessionId,"/topic/get_user",params,createHeaders(sessionId));
	}

	private MessageHeaders createHeaders(String sessionId) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		headerAccessor.setSessionId(sessionId);
		headerAccessor.setLeaveMutable(true);
		return headerAccessor.getMessageHeaders();
	}
}
