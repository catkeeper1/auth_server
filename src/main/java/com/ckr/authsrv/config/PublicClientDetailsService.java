package com.ckr.authsrv.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ruoli.chen on 27/02/2017.
 */
public class PublicClientDetailsService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails details = new BaseClientDetails();

        details.setClientId("PUBLIC_CLIENT");

        Collection<String> grantTypes = new ArrayList<String>();
        grantTypes.add("password");
        details.setAuthorizedGrantTypes(grantTypes);

        return details;
    }
}
