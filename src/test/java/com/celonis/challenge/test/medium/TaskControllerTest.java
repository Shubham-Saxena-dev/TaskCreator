package com.celonis.challenge.test.medium;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient(timeout = "36000")
public class TaskControllerTest extends AbstractMediumTests {

    @Autowired
    private WebTestClient webTestClient;

  /*  @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToServer().defaultHeader(HEADER_NAME, HEADER_VALUE)
                .build();
    }*/

    @Test
    public void giveTaskController_whenValidInputs_thenSucceed() {

        //startCounter
        webTestClient.post()
                .uri("/api/tasks/counter?x=1&y=4")
                .header(HEADER_NAME, HEADER_VALUE)
                .exchange().expectStatus().isOk();

        //getProgress
        webTestClient.get()
                .uri("/api/tasks/counter/1")
                .header(HEADER_NAME, HEADER_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .isEqualTo(100);

        //cancelCounter
        webTestClient.delete()
                .uri("/api/tasks/counter/1")
                .header(HEADER_NAME, HEADER_VALUE)
                .exchange()
                .expectStatus().isOk();
    }

}