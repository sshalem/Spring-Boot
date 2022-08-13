package com.sse.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NewsController {

	public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	// method for client subscription
	// Establishes the Connection with Client
	@CrossOrigin
	@RequestMapping(value = "/shabtay", consumes = MediaType.ALL_VALUE)
	public SseEmitter subscribe() {

		// I add here the Long timeout value Long.MAX_VALUE
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

		try {
			// send event to the client
			sseEmitter.send(SseEmitter.event().name("INIT"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// I need to add this line of code
		// Otherwise I will get error of
		// "java.lang.IllegalStateException: ResponseBodyEmitter is already set
		// complete"
		// This code also handles the warning of "Async request timed out"
		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

		emitters.add(sseEmitter);
		return sseEmitter;
	}

	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestParam String title, @RequestParam String text) {

		// this creates a JSON object
		// with keys of :
		// title , text
		// Then converts them to a String
		// This is send with the
		// On the FrontEnd , I need to use JSON.parse method in order to separate the
		// String back to a JSON
		String freshNews = new JSONObject().put("title", title).put("text", text).toString();

		// here I loop all over the emitters and send event to all clients
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().name("latestNews").data(freshNews));
			} catch (IOException e) {
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
}
