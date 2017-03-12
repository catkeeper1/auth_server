package com.ckr.authsrv.config;

import com.ckr.authsrv.security.CustomizedAuthenticationEntryPoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("RESOURCE_ID").stateless(false);
        resources.authenticationEntryPoint(new CustomizedAuthenticationEntryPoint());
        //resources.
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/hello").access("#oauth2.hasScope('read')")
        .and()
            .authorizeRequests()
            .anyRequest()
            .permitAll();



    }

}
