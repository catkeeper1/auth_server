package com.ckr.authsrv;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by Administrator on 2017/2/26.
 */

@SpringBootApplication
@EnableAuthorizationServer
public class Application {
    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class)
                .properties("spring.config.name=auth_server").run(args);
    }

}
