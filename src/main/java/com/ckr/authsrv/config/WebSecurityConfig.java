package com.ckr.authsrv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/26.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll();



    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication();

    }

    @Bean
    public PermissionEvaluator permissionEvaluator(){
        return new CustomizedPermissionEvaluator();
    }






    public static class CustomizedPermissionEvaluator implements PermissionEvaluator {



        @Override
        public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
            System.out.println("hasPermission "+targetDomainObject);
            System.out.println(permission);
            return true;
        }

        @Override
        public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
            // not supported
            return false;
        }
    }
}
