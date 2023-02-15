package com.sse.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NewsController {

	/**
	 * I create this List<SseEmitter> because I can have multiple browsers sending
	 * connection requests
	 * 
	 * @create a connection request (these 2 lines of JavaScript):
	 *         const url = "http://localhost:8080/subscribe";
	 *         const eventSource = new EventSource(url);
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
	@RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
	public SseEmitter subscribe() {

		// I add here the Long timeout value Long.MAX_VALUE 
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			// send event to the client that connection is established
			// with name I gave it : "Establish connection"
			sseEmitter.send(SseEmitter.event().name("Establish connection"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// I need to add this line of code 
		// Otherwise I will get error of :
		// "java.lang.IllegalStateException: ResponseBodyEmitter is already set complete" 
		// This code also handles the warning of "Async request timed out"         
		sseEmitter.onCompletion(()-> emitters.remove(sseEmitter));
		emitters.add(sseEmitter);
		return sseEmitter;
	}

	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestParam String freshNews) {

				
		// here I loop all over the emitters 
		// and send event to all clients
		for(SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().name("latestNews").data(new FreshNews(freshNews)));
			} catch (IOException e) {    
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
	
	class FreshNews {
		private String freshNews;

		public FreshNews(String freshNews) {
			super();
			this.freshNews = freshNews;
		}

		public String getFreshNews() {
			return freshNews;
		}

		public void setFreshNews(String freshNews) {
			this.freshNews = freshNews;
		}		
	}
}
