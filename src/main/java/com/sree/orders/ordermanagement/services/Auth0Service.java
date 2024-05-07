package com.sree.orders.ordermanagement.services;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sree.orders.ordermanagement.config.Auth0Config;
import com.sree.orders.ordermanagement.config.Auth0PublicKeyProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Auth0Service {

    @Autowired
    private Auth0Config auth0Config;
    @Autowired
    private Auth0PublicKeyProvider auth0PublicKeyProvider;

    public String verifyAccessToken(String accessToken) {
        try{
            DecodedJWT decodeJwt = new JWT().decodeJwt(accessToken);
            JWTVerifier verifier = getVerifier(decodeJwt.getKeyId());
            verifier.verify(decodeJwt);
            return decodeJwt.getSubject();
        } catch(Exception e) {
            throw new BadCredentialsException("accessToken was not found");
        }
    }

    public JWTVerifier getVerifier(String publicKeyId) {
        RSAPublicKey publicKey = auth0PublicKeyProvider.getPublicKey(publicKeyId);
        return JWT.require(Algorithm.RSA256(publicKey, null))
        .withAudience(auth0Config.getAudience())
        .withIssuer("https://"+auth0Config.getDomain()+"/").build();
    }

}
