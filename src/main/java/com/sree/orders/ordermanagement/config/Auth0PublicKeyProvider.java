package com.sree.orders.ordermanagement.config;

import java.security.interfaces.RSAPublicKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;

@Component
public class Auth0PublicKeyProvider {

    private Auth0Config auth0Config;

    Auth0PublicKeyProvider(Auth0Config auth0Config) {
        this.auth0Config = auth0Config;
    }

    public RSAPublicKey getPublicKey(String keyId) {
        try {
            JwkProvider jwkProvider = new JwkProviderBuilder(auth0Config.getDomain()).cached(true).build();
            return (RSAPublicKey) jwkProvider.get(keyId).getPublicKey();
        } catch (JwkException e) {
            throw new BadCredentialsException("JWK Exception");
        }
    }
    
}
