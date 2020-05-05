package phambaolong.news.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	private String value;
	
	public HttpUtils(String value) {
		this.value = value;
	}
	
	public static HttpUtils toString(BufferedReader reader) {
		StringBuilder jsonToString = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				jsonToString.append(line);
				System.out.println(jsonToString.toString());
			}
			return new HttpUtils(jsonToString.toString());
		} catch (IOException e) {
			return null;
		}
	}
	
	public <T> T toModel(Class <T> tClass){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(this.value, tClass);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}
