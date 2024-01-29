package com.latihan.java.spring.design.pattern.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    private JsonHelper(){}
    public static <T> String valueOf(T object)  {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            return "ERROR";
        }
    }

}
