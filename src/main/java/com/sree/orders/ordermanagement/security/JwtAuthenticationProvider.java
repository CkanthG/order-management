package com.sree.orders.ordermanagement.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken auth = (JwtAuthenticationToken) authentication;
        String credentials = auth.getCredentials();
        Object principal = auth.getPrincipal();
        log.info("Credentials:{}\nPrincipal:{}", credentials, principal);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == authentication.getClass();
    }
    
}
