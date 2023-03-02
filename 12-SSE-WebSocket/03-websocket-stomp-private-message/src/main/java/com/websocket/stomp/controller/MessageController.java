package com.websocket.stomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.websocket.stomp.entity.Message;

@Controller
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/application")
	@SendTo("/all/messages")
	public Message sendMessage(@Payload Message message, StompHeaderAccessor stompHeaderAccessor) throws Exception {
		return message;
	}

	@MessageMapping("/private-message")
	public Message recMessage(@Payload Message message) {
		simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
		System.out.println(message);
		return message;
	}

}
