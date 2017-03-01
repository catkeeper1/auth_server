package com.ckr.authsrv.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
        grantTypes.add("refresh_token");
        details.setAuthorizedGrantTypes(grantTypes);

        //default 3600 seconds
        Integer validitySeconds = 3600;

        HttpServletRequest webRequest =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            //the client can use validSeconds parameter to override the default valid time.
            validitySeconds = new Integer(webRequest.getParameter("validSeconds"));
        }catch(Exception e){

        }

        details.setAccessTokenValiditySeconds(validitySeconds);

        HashSet<String> scopes = new HashSet<String>();
        scopes.add("all");

        details.setScope(scopes);



        return details;
    }
}
