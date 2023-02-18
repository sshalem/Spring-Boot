package com.sse.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NewsController {

	/**
	 * I create this List<SseEmitter> because I can have multiple browsers sending
	 * connection requests
	 * 
	 * @create a connection request (the line below of JavaScript):
	 *         const eventSource = new EventSource("http://localhost:8080/createConnection");
	 * 
	 * @CopyOnWriteArrayList is synchronized, thread safe , But is slower than ArrayList..
	 */
	public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	/**
	 * method for client subscription, Establishes the Connection with Client.
	 * 
	 * @I must consume MediaType.ALL_VALUE
	 */
	@CrossOrigin
	@GetMapping(path = "/createConnection", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	
	public SseEmitter createConnection() {

		// I add here the Long timeout value Long.MAX_VALUE 
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			// First : send a connection request message to the client, to established connection 
			sseEmitter.send(SseEmitter.event().name("Establish connection"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// I need to add this line of code 
		// Otherwise I will get error of :
		// "java.lang.IllegalStateException: ResponseBodyEmitter is already set complete" 
		// This code also handles the warning of "Async request timed out"         
		sseEmitter.onCompletion(()-> emitters.remove(sseEmitter));
		sseEmitter.onError((e) -> emitters.remove(sseEmitter));
		sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
		emitters.add(sseEmitter);
		return sseEmitter;
	}

	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestBody Object freshNews) {

				
		// here I loop all over the emitters 
		// and send event (push events) to all clients
		for(SseEmitter emitter : emitters) {
			try {
				// SInce I use event Handler on my front end
				// Thus I need to :
				// 1. define the  ---> name("message")
				// 2. on frontEnd I use ---> eventSource.onmessage = function (event)
				emitter.send(SseEmitter.event().name("message").data(freshNews));
			} catch (IOException e) {    
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
	
	static class FreshNews {
		private String freshNews;

		public FreshNews() {
			super();		
		}		

		public String getFreshNews() {
			return freshNews;
		}

		public void setFreshNews(String freshNews) {
			this.freshNews = freshNews;
		}		
	}
}
