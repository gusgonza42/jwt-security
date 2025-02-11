package com.auth.security.service;

import com.auth.security.dto.AuthRequest;
import com.auth.security.dto.AuthResponse;
import com.auth.security.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.auth.security.util.Utils.createCustomMessage;
import static com.auth.security.util.Utils.printMessage;

@Service
public class AuthService {
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public AuthService( JwtTokenUtils jwtTokenUtils ) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public ResponseEntity< String > getHello( ) {
        printMessage( "Get From Api" );
        return ResponseEntity.status( HttpStatus.OK ).body( "Hello from Auth Project" );
    }

    public ResponseEntity< AuthResponse > login( AuthRequest authRequest ) {
        String token = jwtTokenUtils.generateToken( authRequest.getUsername( ) );
        AuthResponse authResponse = new AuthResponse( token );
        String message = createCustomMessage( authRequest.getUsername( ) , "login" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.OK ).body( authResponse );
    }

    public ResponseEntity< AuthResponse > register( AuthRequest authRequest ) {
        // Aquí puedes agregar la lógica de registro del usuario
        String token = jwtTokenUtils.generateToken( authRequest.getUsername( ) );
        AuthResponse authResponse = new AuthResponse( token );
        String message = createCustomMessage( authRequest.getUsername( ) , "register" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.CREATED ).body( authResponse );
    }
}