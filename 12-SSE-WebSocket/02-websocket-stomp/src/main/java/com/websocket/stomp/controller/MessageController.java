package com.websocket.stomp.controller;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.websocket.stomp.entity.Message;

@Controller
public class MessageController {

	/**
	 * @MessageMapping annotation ensures that, if a message is sent to the
	 *                 "/app/application" (/`@prefix from WebSocketConfig
	 *                 class`/application) destination, the sendMessage() method is
	 *                 called. Note the Spring adds the `/app` prefix for us
	 * 
	 *                 the sendMessage() method creates a Message object and returns
	 *                 it. The return value is broadcast to all subscribers of
	 *                 "/all/messages, as specified in the @SendTo annotation
	 * 
	 *                 We add this to tell Spring to send the return value to the
	 *                 given endpoint. All we are doing here is taking messages sent
	 *                 from one endpoint and redirecting to another
	 * 
	 *                 our method receives messages from /app/application. We also
	 *                 have the @SendTo annotation with the value /all/messages.
	 *                 this `/all` comes from the configuration class `registry.enableSimpleBroker("/all")` 
	 */

	// Mapped as /app/application
	@MessageMapping("/application")
	@SendTo("/all/messages")
	public Message sendMessage(@Payload Message message, StompHeaderAccessor stompHeaderAccessor) throws Exception {
		
		// This command gets the header of the 
		// 'First' "SUBSCRIBE" connection made, then each time >>>SEND , the header is send
		Object addressNativeHeader = stompHeaderAccessor.getFirstNativeHeader("send-Header");
		System.out.println(addressNativeHeader);
	
		// This headers are made by the client each message he sends
		MessageHeaders messageHeaders = stompHeaderAccessor.getMessageHeaders();
		System.out.println(messageHeaders);	
		
		return message;
	}

}
