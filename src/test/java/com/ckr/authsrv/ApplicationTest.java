package com.ckr.authsrv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(value = "spring.config.name=auth_server",
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = Application.class)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        ResponseEntity<String> body = this.restTemplate.getForEntity("/hello", String.class);

        System.out.println(body);
        System.out.println(body.getStatusCode());
        //assertThat(body).isEqualTo("Hello World");
    }

}
