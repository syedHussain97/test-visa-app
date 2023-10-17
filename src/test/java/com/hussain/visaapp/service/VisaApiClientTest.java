package com.hussain.visaapp.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.hussain.visaapp.model.VisaHelloWorldResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


@SuppressWarnings("SpringBootApplicationProperties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"project.url=https://sandbox.api.visa.com/vdp/helloworld"})
class VisaApiClientTest {

  @MockBean
  private RestTemplate visaApiRestTemplate;

  private MockRestServiceServer mockServer;

  @Autowired
  private VisaApiClient TEST_ME;


  private final ObjectMapper mapper = new ObjectMapper();

  @BeforeEach
  public void init() {
    mockServer = MockRestServiceServer.bindTo(visaApiRestTemplate).build();
    final JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
    mapper.registerModule(javaTimeModule);
  }

  @Test
  void test_restTemplateReturns()
      throws JsonProcessingException, URISyntaxException {
    final VisaHelloWorldResponse visaHelloWorldResponse = new VisaHelloWorldResponse();
    visaHelloWorldResponse.setMessage("helloworld=");
    visaHelloWorldResponse.setTimestamp(LocalDateTime.now());

    mockServer.expect(ExpectedCount.once(),
            requestTo(new URI("https://sandbox.api.visa.com/vdp/helloworld")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(mapper.writeValueAsString(visaHelloWorldResponse))
        );

    Assertions.assertEquals("helloworld=", visaHelloWorldResponse.getMessage());
  }

  @Test
  void testVisaApiClient_responseIsValid() {
    final VisaHelloWorldResponse visaHelloWorldResponse = new VisaHelloWorldResponse();
    visaHelloWorldResponse.setMessage("helloworld=");
    visaHelloWorldResponse.setTimestamp(LocalDateTime.now());

    final ResponseEntity<VisaHelloWorldResponse> entity = new ResponseEntity<>(
        visaHelloWorldResponse,
        HttpStatus.OK);

    when(visaApiRestTemplate.getForEntity(
        anyString(),
        ArgumentMatchers.<Class<VisaHelloWorldResponse>>any())).thenReturn(entity);

    Assertions.assertEquals("helloworld=", TEST_ME.get());
  }

  @Test
  void testVisaApiClient_responseIsInvalid() {

    final ResponseEntity<String> entity = new ResponseEntity<>(
        "API CHANGED",
        HttpStatus.ACCEPTED);

    when(visaApiRestTemplate.getForEntity(
        anyString(),
        ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

    Assertions.assertNull(TEST_ME.get());
  }

}
