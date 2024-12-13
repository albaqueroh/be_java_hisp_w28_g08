package com.mercadolibre.sprint1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CResourceUtils {

	public static final ObjectMapper MAPPER = new ObjectMapper()
					.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
					.registerModule(new JavaTimeModule());;

}
