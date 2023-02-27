package com.websocket.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.sockjs.transport.handler.WebSocketTransportHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/all");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/ws-stomp-endpoint").withSockJS().setInterceptors(new HttpHandshakeInterceptor());
		registry.addEndpoint("/ws-stomp-endpoint").setHandshakeHandler(handshakeHandler()).withSockJS();
//		registry.addEndpoint("/ws-stomp-endpoint").withSockJS();
	}

	private WebSocketTransportHandler handshakeHandler() {
		
		HttpHandshakeInterceptor httpHandshakeInterceptor = new HttpHandshakeInterceptor();
		
		httpHandshakeInterceptor.
		
		WebSocketTransportHandler handler = new WebSocketTransportHandler();
		return null;
	}

}
