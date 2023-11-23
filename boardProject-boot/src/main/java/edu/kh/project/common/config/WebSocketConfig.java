package edu.kh.project.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import edu.kh.project.chatting.model.websocket.ChattingWebsocketHandler;

@Configuration
@EnableWebSocket // 여기에서 WebSocket 사용함을 명시
public class WebSocketConfig implements WebSocketConfigurer{

	@Autowired
	private ChattingWebsocketHandler chattingWebsocketHandler;
	
	@Autowired
	private HandshakeInterceptor handshakeInterceptor;
	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(chattingWebsocketHandler, "/chattingSock")
								.addInterceptors(handshakeInterceptor)
								.setAllowedOriginPatterns("http://localhost/", "http://127.0.0.1")
								.withSockJS();
		
		// CORS = Cross Origin Resorce Sharing
		// origin? http://localhost:8080
		
	}

}
