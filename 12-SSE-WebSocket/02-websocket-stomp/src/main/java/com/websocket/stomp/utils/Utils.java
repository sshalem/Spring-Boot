package com.websocket.stomp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static void consoleAsJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonForm = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		System.out.println(jsonForm);
	}
}
