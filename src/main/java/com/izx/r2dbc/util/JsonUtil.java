package com.izx.r2dbc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    static{
        objectMapper.registerModule(new JavaTimeModule());
    }

    private JsonUtil() {

    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        String s = null;
        try {
            s = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("obj2String error ", e);
        }
        return s;
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if(str == null || str.length()==0 || clazz == null){
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class)? (T)str : objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            log.error("string2Obj error ", e);
        }
        return t;
    }
}
