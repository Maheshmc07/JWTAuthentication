package org.example.JWTauthenticatinDemo.Configurations;

import org.example.JWTauthenticatinDemo.Filters.JWTFilter;
import org.example.JWTauthenticatinDemo.Service.CustomUserDetailesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigs {

    @Autowired
    private JWTFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//this filter chain is used to remove default form Authentiaction ans keep our Basic Auth
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/auth**").permitAll()  // Allow public endpoints
                        .anyRequest().authenticated()              // Secure all others
                )
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);// Enable Basic Auth

        return http.build();}

    @Bean
    protected PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
@Autowired
    private CustomUserDetailesService customUserDetailesService;


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService customUserDetailesService,PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();//this provider will handle Basic Auth and Form Based Authentication
        daoAuthenticationProvider.setUserDetailsService(customUserDetailesService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);

    }

}
