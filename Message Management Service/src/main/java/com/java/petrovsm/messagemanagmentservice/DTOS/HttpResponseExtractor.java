package com.java.petrovsm.messagemanagmentservice.DTOS;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class HttpResponseExtractor {
    public static <T> T extractDataFromHttpClientResponse(Object data, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(((HashMap<String, T>) data).get("data"), clazz);
    }
}
