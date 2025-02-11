package com.auth.security.config;

import com.auth.security.exception.JwtAuthenticationEntryPoint;
import com.auth.security.filter.JwtRequestFilter;
import com.auth.security.util.JwtTokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenUtils jwtTokenUtils;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig( JwtTokenUtils jwtTokenUtils , JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http.csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers( "/auth/login" , "/auth/register" ).permitAll( )
                        .anyRequest( ).authenticated( )
                )
                .exceptionHandling( exception -> exception
                        .authenticationEntryPoint( jwtAuthenticationEntryPoint )
                )
                .addFilterBefore( new JwtRequestFilter( jwtTokenUtils ) , UsernamePasswordAuthenticationFilter.class );
        return http.build( );
    }
}