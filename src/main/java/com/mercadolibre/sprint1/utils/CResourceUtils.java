package com.mercadolibre.sprint1.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CResourceUtils {

	public static final ObjectMapper MAPPER = new ObjectMapper();

	public static <T> List<T> loadDataFromJson(String filename) throws IOException {
		File file = ResourceUtils.getFile("classpath:" + filename);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file, new TypeReference<List<T>>(){});
	}

	public static LocalDate formatToLocalDate(String date){
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(date, inputFormatter);
	}
}
