package com.ckr.authsrv.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by ruoli.chen on 28/02/2017.
 */
public class CustomizedPermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("hasPermission \n" + targetDomainObject);
        System.out.println(permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
    }
}
