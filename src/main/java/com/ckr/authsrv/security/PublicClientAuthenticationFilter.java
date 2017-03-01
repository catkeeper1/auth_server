package com.ckr.authsrv.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by ruoli.chen on 28/02/2017.
 */
public class PublicClientAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static Logger LOG = LoggerFactory.getLogger(PublicClientAuthenticationFilter.class);

    //public static final String PUBLIC_CLIENT_USER_NAME = "PUBLIC_CLIENT";


    public PublicClientAuthenticationFilter(){
        super();

        AuthenticationManager auth = new AuthenticationManager() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                LOG.debug("generate public client authentication");
                return new PublicClientAuthentication();
            }
        };

        this.setAuthenticationManager(auth);
    }


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return PublicClientAuthentication.PUBLIC_CLIENT_USER_NAME;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "";
    }
}
