package com.sample.microservices.circuitbreaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sample.microservices.circuitbreaker.service.SlowService;

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
public class Resilience4JControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private SlowService slowService;
 /*   
  @RepeatedTest(10)
  public void test_delay(RepetitionInfo repetitionInfo) {
    int delay = 3 + (repetitionInfo.getCurrentRepetition() % 2);
    webTestClient.get()
        .uri("/api/delay/{delay}", delay)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isOk();
  }
*/  
  @RepeatedTest(10)
  public void test_slowMethod(RepetitionInfo repetitionInfo) {
    int slow = 3 + (repetitionInfo.getCurrentRepetition() % 2);
    webTestClient.get()
        .uri("/api/slow/{slow}", slow)
        .header(HttpHeaders.CONTENT_TYPE)
        .exchange()
        .expectStatus()
        .isOk();
  }

}
