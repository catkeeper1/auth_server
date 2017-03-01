package com.ckr.authsrv.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by ruoli.chen on 28/02/2017.
 */
public class PublicClientAuthentication implements Authentication {

    public static final String PUBLIC_CLIENT_USER_NAME = "PUBLIC_CLIENT";


    public PublicClientAuthentication() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public Object getPrincipal() {
        return PUBLIC_CLIENT_USER_NAME;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("This method is not supported.");
    }

    @Override
    public String getName() {
        return PUBLIC_CLIENT_USER_NAME;
    }
}
