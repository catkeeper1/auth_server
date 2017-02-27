package com.ckr.authsrv.config;

import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruoli.chen on 27/02/2017.
 */
public class PublicClientDetailsServiceBuilder  extends
        ClientDetailsServiceBuilder<PublicClientDetailsServiceBuilder> {


    @Override
    protected void addClient(String clientId, ClientDetails value) {
        // this is not a in memory client detail service builder, no need to add client detail to the
        // client detail service so do nothing here.
    }

    @Override
    protected ClientDetailsService performBuild() {
        ClientDetailsService clientDetailsService = new PublicClientDetailsService();

        return clientDetailsService;
    }
}