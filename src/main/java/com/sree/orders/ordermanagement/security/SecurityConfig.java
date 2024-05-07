package com.sree.orders.ordermanagement.security;

import com.sree.orders.ordermanagement.services.Auth0Service;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private Auth0Service auth0Service;

    public SecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider, Auth0Service auth0Service) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.auth0Service = auth0Service;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(jwtAuthenticationProvider)
                .authorizeHttpRequests(t ->
                        t.requestMatchers("/","/images/**").permitAll().anyRequest().authenticated()
                        ).addFilterAfter(this::filter, BasicAuthenticationFilter.class)
                .build();
    }

    public void filter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorization = ((HttpServletRequest) request).getHeader("authorization");
        log.info("authorization : {}", authorization);
        auth0Service.verifyAccessToken(authorization.replace("Bearer ", ""));
        chain.doFilter(request, response);
    }
}
