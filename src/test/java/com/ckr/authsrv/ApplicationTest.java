package com.ckr.authsrv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(value = "spring.config.name=auth_server",
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = Application.class)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {

        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap();
        request.set("username", "userA");
        request.set("password", "passwordA");
        request.set("grant_type", "password");

        Map<String, Object> token = this.restTemplate.postForObject("/oauth/token", request, Map.class);

        String accessToken = (String) token.get("access_token");
        String refreshToken = (String) token.get("refresh_token");

        System.out.println(token);
        System.out.println(accessToken);
        System.out.println(refreshToken);

        request = new LinkedMultiValueMap();
        request.set("grant_type", "refresh_token");
        request.set("refresh_token", refreshToken);

        token = this.restTemplate.postForObject("/oauth/token", request, Map.class);

        accessToken = (String) token.get("access_token");
        System.out.println(token);
        System.out.println(accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        ResponseEntity<String> body = restTemplate.exchange("/hello", HttpMethod.POST, entity, String.class);
        System.out.println(body);
        //assertThat(body).isEqualTo("Hello World");
    }

}
