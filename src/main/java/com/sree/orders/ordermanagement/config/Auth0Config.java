package com.sree.orders.ordermanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="auth0")
public class Auth0Config {
    private String clientId;
    private String clientSecret;
    private String domain;
    private String audience;

    public String setClientId(String clientId){
        return this.clientId = clientId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String setClientSecret(String clientSecret){
        return this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String setDomain(String domain){
        return this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }

    public String setAudience(String audience){
        return this.audience = audience;
    }

    public String getAudience() {
        return this.audience;
    }

    
}
