package com.auth.security.config;

import com.auth.security.exception.JwtAuthenticationEntryPoint;
import com.auth.security.filter.JwtRequestFilter;
import com.auth.security.service.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenService jwtTokenService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig( JwtTokenService jwtTokenService , JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ) {
        this.jwtTokenService = jwtTokenService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http.csrf( csrf -> csrf.disable( ) )
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers( "/auth/login" , "/auth/register" ).permitAll( )
                        .anyRequest( ).authenticated( )
                )
                .exceptionHandling( exception -> exception
                        .authenticationEntryPoint( jwtAuthenticationEntryPoint )
                )
                .addFilterBefore( new JwtRequestFilter( jwtTokenService ) , UsernamePasswordAuthenticationFilter.class );
        return http.build( );
    }
}