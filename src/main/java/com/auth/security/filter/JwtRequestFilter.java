package com.auth.security.filter;

import com.auth.security.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;

    public JwtRequestFilter( JwtTokenService jwtTokenService ) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request , HttpServletResponse response , FilterChain filterChain )
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader( "Authorization" );
        String token = null;

        if ( authorizationHeader != null && authorizationHeader.startsWith( "Bearer " ) ) {
            token = authorizationHeader.substring( 7 );
        }

        if ( token != null ) {
            if ( jwtTokenService.isTokenExpired( token ) ) {
                String refreshedToken = jwtTokenService.refreshToken( jwtTokenService.extractUsername( token ) );
                response.setHeader( "Authorization" , "Bearer " + refreshedToken );
            } else if ( jwtTokenService.isTokenNearExpiry( token ) ) {
                String refreshedToken = jwtTokenService.refreshToken( jwtTokenService.extractUsername( token ) );
                response.setHeader( "Authorization" , "Bearer " + refreshedToken );
                UsernamePasswordAuthenticationToken auth = jwtTokenService.getAuthentication( refreshedToken );
                SecurityContextHolder.getContext( ).setAuthentication( auth );
            } else if ( jwtTokenService.isValidToken( token ) ) {
                UsernamePasswordAuthenticationToken auth = jwtTokenService.getAuthentication( token );
                SecurityContextHolder.getContext( ).setAuthentication( auth );
            }
        }

        filterChain.doFilter( request , response );
    }
}