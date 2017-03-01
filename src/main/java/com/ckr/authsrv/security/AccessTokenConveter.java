package com.ckr.authsrv.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruoli.chen on 01/03/2017.
 */
public class AccessTokenConveter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

        Map<String, Object> addInfo = new HashMap();
        addInfo.putAll(token.getAdditionalInformation());
        /* you can add additional info to the access token as below
        addInfo.put("info1", "abc");
        */
        token.setAdditionalInformation(addInfo);

        return super.enhance(accessToken, authentication);

    }
}
