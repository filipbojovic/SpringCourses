package com.fika.rest.webservices.restfulwebservices.socialapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1) all requests should be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated() // all requests that are coming should be authenticated
        );

        // 2) if a request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());

        // 3) CSRF -> post, put
        http.csrf().disable();


        return http.build();
    }

}
