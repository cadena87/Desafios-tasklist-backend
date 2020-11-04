package com.example.tasklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class RestConfig extends WebSecurityConfigurerAdapter {

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
            .headers().addHeaderWriter(
            new StaticHeadersWriter("Access-Control-Allow-Origin", "*")).and()
            .addFilterBefore(corsFilter(), SessionManagementFilter.class)
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .anyRequest()
             .permitAll();
    }
}
