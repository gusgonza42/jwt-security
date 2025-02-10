package com.auth.security.service;

import com.auth.security.dto.AuthRequest;
import com.auth.security.dto.AuthResponse;
import com.auth.security.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthService( JwtTokenService jwtTokenService ) {
        this.jwtTokenService = jwtTokenService;
    }

    public ResponseEntity< AuthResponse > login( AuthRequest authRequest ) {
        String token = jwtTokenService.generateToken( authRequest.getUsername( ) );
        AuthResponse authResponse = new AuthResponse( token );
        String message = Utils.createCustomMessage( authRequest.getUsername( ) , "login" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.OK ).body( authResponse );
    }

    public ResponseEntity< AuthResponse > register( AuthRequest authRequest ) {
        // Aquí puedes agregar la lógica de registro del usuario
        String token = jwtTokenService.generateToken( authRequest.getUsername( ) );
        AuthResponse authResponse = new AuthResponse( token );
        String message = Utils.createCustomMessage( authRequest.getUsername( ) , "register" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.CREATED ).body( authResponse );
    }
}