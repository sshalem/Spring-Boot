package com.filesystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SysoutToJsonFormat {

	public static void jsonFormat(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonFormat = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		System.out.println(jsonFormat);
	}
}
