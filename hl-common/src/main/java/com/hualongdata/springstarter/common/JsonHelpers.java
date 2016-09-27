package com.hualongdata.springstarter.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;

import java.text.SimpleDateFormat;

/**
 * Created by yangbajing on 16-9-5.
 */
public class JsonHelpers {
    public static ObjectMapper createObjectMapper(ObjectMapper objectMapper) {
        return objectMapper
                .registerModule(new DefaultScalaModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper createObjectMapper() {
//                .registerModule(new Jdk7Module())
        return createObjectMapper(new ObjectMapper());
    }

}
