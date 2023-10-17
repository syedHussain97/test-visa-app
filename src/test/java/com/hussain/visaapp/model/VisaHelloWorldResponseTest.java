package com.hussain.visaapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisaHelloWorldResponseTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void init() {
    final JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
    objectMapper.registerModule(javaTimeModule);
  }

  @Test
  void testJsonGenerated() throws JsonProcessingException {
    final VisaHelloWorldResponse visaHelloWorldResponse = new VisaHelloWorldResponse();
    visaHelloWorldResponse.setTimestamp(LocalDateTime.of(2019, 12, 21, 6, 0));
    visaHelloWorldResponse.setMessage("testMessage");

    final String writeValueAsString = objectMapper.writeValueAsString(visaHelloWorldResponse);

    final String responseExpected = "{\"timestamp\":[2019,12,21,6,0],\"message\":\"testMessage\"}";
    Assertions.assertEquals(responseExpected, writeValueAsString);
  }


}
