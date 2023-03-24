package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
        @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/loginGoogle/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login();
//                .loginPage("/loginGoogle")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll();

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/loginGoogle/*").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .oauth2Login()
//                .loginPage("/loginGoogle")
//                .defaultSuccessUrl("/loginGoogle", true) // set default success URL
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll();
//
//        return http.build();
//    }
}
