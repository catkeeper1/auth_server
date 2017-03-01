package com.ckr.authsrv.config;

import com.ckr.authsrv.security.AccessTokenConveter;
import com.ckr.authsrv.security.PublicClientAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * Created by Administrator on 2017/2/26.
 */
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${authsrv.security.keystore.name}")
    private String keyStoreName;

    @Value("${authsrv.security.keystore.password}")
    private String keyStorePassword;

    @Value("${authsrv.security.keystore.key.alias}")
    private String keyAlias;

    @Value("${authsrv.security.keystore.key.password}")
    private String keyPassword;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        //when JwtAccessTokenConverter is used here, JwtTokenStore will be used automatically as well so that
        //no need to worry about that memory is consumed because token is stored.
                 .accessTokenConverter(accessTokenConverter());




    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer.tokenKeyAccess("permitAll()")
                   .addTokenEndpointAuthenticationFilter(new PublicClientAuthenticationFilter());


    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter result = new AccessTokenConveter();
        ClassPathResource res = new ClassPathResource(keyStoreName);

        KeyStoreKeyFactory keyStoreFactory = new KeyStoreKeyFactory(res, keyStorePassword.toCharArray());


        result.setKeyPair(keyStoreFactory.getKeyPair(keyAlias, keyPassword.toCharArray()));
        return result;
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
  /*      clients.inMemory()
                .withClient("my-trusted-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(60)
                .refreshTokenValiditySeconds(160)
                .and()
                .withClient("my-client-with-registered-redirect")
                .authorizedGrantTypes("authorization_code")
                .authorities("ROLE_CLIENT")
                .scopes("read", "trust")
                .redirectUris("http://anywhere?key=value")
                .and()
                .withClient("my-client-with-secret")
                .authorizedGrantTypes( "password")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write")
                .secret("secret");
*/
        clients.setBuilder(new PublicClientDetailsServiceBuilder());

    }
}
