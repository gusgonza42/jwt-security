package com.auth.security.filter;

import com.auth.security.util.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    public JwtRequestFilter( JwtTokenUtils jwtTokenUtils ) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request , HttpServletResponse response , FilterChain filterChain )
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader( "Authorization" );
        String token = null;

        if ( authorizationHeader != null && authorizationHeader.startsWith( "Bearer " ) ) {
            token = authorizationHeader.substring( 7 );
        }

        try {
            if ( token != null ) {
                if ( jwtTokenUtils.isTokenExpired( token ) ) {
                    String refreshedToken = jwtTokenUtils.refreshToken( jwtTokenUtils.extractUsername( token ) );
                    response.setHeader( "Authorization" , "Bearer " + refreshedToken );
                } else if ( jwtTokenUtils.isTokenNearExpiry( token ) ) {
                    String refreshedToken = jwtTokenUtils.refreshToken( jwtTokenUtils.extractUsername( token ) );
                    response.setHeader( "Authorization" , "Bearer " + refreshedToken );
                    UsernamePasswordAuthenticationToken auth = jwtTokenUtils.getAuthentication( refreshedToken );
                    SecurityContextHolder.getContext( ).setAuthentication( auth );
                } else if ( jwtTokenUtils.isValidToken( token ) ) {
                    UsernamePasswordAuthenticationToken auth = jwtTokenUtils.getAuthentication( token );
                    SecurityContextHolder.getContext( ).setAuthentication( auth );
                }
            }
        } catch ( Exception e ) {
            response.sendError( HttpServletResponse.SC_UNAUTHORIZED , "Invalido o el token esta caducado" );
            return;
        }

        filterChain.doFilter( request , response );
    }
}