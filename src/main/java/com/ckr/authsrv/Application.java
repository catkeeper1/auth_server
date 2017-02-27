package com.ckr.authsrv;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/26.
 */

@SpringBootApplication
@EnableAuthorizationServer
@Controller
public class Application {

    @RequestMapping(value = "/hello")
    @PreAuthorize(" hasPermission('hello', 'view') ")
    public void hello(HttpServletResponse response){
        try {
            response.getWriter().append("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class)
                .properties("spring.config.name=auth_server").run(args);
    }

}
