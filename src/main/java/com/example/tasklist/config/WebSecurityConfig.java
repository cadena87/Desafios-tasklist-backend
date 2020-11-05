package com.example.tasklist.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/task").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/task").permitAll()
                .antMatchers( HttpMethod.PUT, "/api/v1/task/*").permitAll()
                .antMatchers( HttpMethod.DELETE, "/api/v1/task/*").permitAll()
                .and().cors().disable();
    }
}