package com.sse.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@RestController
public class NewsController {

	public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	@CrossOrigin
	@GetMapping(path = "/createConnection", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	
	public SseEmitter createConnection() {

		// I add here the Long timeout value Long.MAX_VALUE 
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			// First : send a connection request message to the client, to established connection 
			SseEventBuilder sseEventBuilder = SseEmitter
					.event()
					.id(UUID.randomUUID().toString().substring(0, 8))
					.name("this is the type field")
					.data("connecting to server");			
			
			sseEmitter.send(sseEventBuilder);					
		} catch (IOException e) {
			e.printStackTrace();
		}
    
		sseEmitter.onCompletion(()-> emitters.remove(sseEmitter));
		sseEmitter.onError((e) -> emitters.remove(sseEmitter));
		sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
		emitters.add(sseEmitter);
		return sseEmitter;
	}

	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestBody Object freshNews) {
				
		for(SseEmitter emitter : emitters) {
			try {
				// SInce I use eventListener in my front end
				// I need to define the same name in the listener here and in my backend
				// BackEnd ---> custom name "latestNews"
				// FrontEnd eventListener ---> "latestNews"
				emitter.send(SseEmitter.event().name("latestNews").data(freshNews));				
			} catch (IOException e) {    
				emitters.remove(emitter);
			}
		}
	}
	
}
