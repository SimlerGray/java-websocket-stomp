package com.example.config;

import com.websocket.example.Utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Map;

/**
 * Created by apple on 17/8/22.
 */
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

	@Autowired
	SocketSessionRegistry webAgentSessionRegistry;

	@Override
	public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionConnectedEvent.getMessage());
		//login get from browser
		MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(sessionConnectedEvent.getMessage(), SimpMessageHeaderAccessor.class);
		accessor.getMessageHeaders();
		Object header = accessor.getHeader("simpConnectMessage");
		GenericMessage<?> generic = (GenericMessage<?>) accessor.getHeader("simpConnectMessage");
		System.out.println(generic.getHeaders().get("nativeHeaders"));
		Object nativeHeaders = generic.getHeaders().get("nativeHeaders");
		System.out.print(JsonUtils.mapToJson(nativeHeaders));
		Map<String, String> map = JsonUtils.parseJsonWithGson(JsonUtils.mapToJson(nativeHeaders));
		String agentId = map.get("login");
		agentId = agentId.replace("[\"", "");
		agentId = agentId.replace("\"]", "");
		String sessionId = sha.getSessionId();
		webAgentSessionRegistry.registerSessionId(agentId, sessionId);
	}
}