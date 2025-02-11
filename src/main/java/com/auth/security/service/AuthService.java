package com.auth.security.service;

import com.auth.security.dto.AuthRequest;
import com.auth.security.dto.AuthResponse;
import com.auth.security.model.User;
import com.auth.security.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public AuthService( JwtTokenUtils jwtTokenUtils , UserRepository userRepository ) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.userRepository = userRepository;
    }

    public ResponseEntity< String > getHello( ) {
        printMessage( "Get From Api" );
        return ResponseEntity.status( HttpStatus.OK ).body( "Hello from Auth Project" );
    }

    public ResponseEntity< AuthResponse > login( AuthRequest authRequest ) {
        User user = userRepository.findByUsername( authRequest.getUsername( ) );
        if ( user == null ) {
            user = userRepository.findByEmail( authRequest.getEmail( ) );
        }
        if ( user == null || ! user.getPassword( ).equals( authRequest.getPassword( ) ) ) {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( null );
        }

        String token = user.getToken( );
        if ( token == null || ! jwtTokenUtils.isValidToken( token ) ) {
            token = jwtTokenUtils.generateToken( user.getUsername( ) );
            user.setToken( token );
            userRepository.save( user );
        }

        AuthResponse authResponse = new AuthResponse( token );
        String message = createCustomMessage( user.getUsername( ) , "login" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.OK ).body( authResponse );
    }

    public ResponseEntity< AuthResponse > register( AuthRequest authRequest ) {
        User userByUsername = userRepository.findByUsername( authRequest.getUsername( ) );
        User userByEmail = userRepository.findByEmail( authRequest.getEmail( ) );

        if ( userByUsername != null || userByEmail != null ) {
            return ResponseEntity.status( HttpStatus.CONFLICT ).body( null );
        }

        // Aquí puedes agregar la lógica de registro del usuario
        String token = jwtTokenUtils.generateToken( authRequest.getUsername( ) );
        User user = new User( );
        user.setUsername( authRequest.getUsername( ) );
        user.setEmail( authRequest.getEmail( ) );
        user.setPassword( authRequest.getPassword( ) );
        user.setToken( token );
        userRepository.save( user );

        AuthResponse authResponse = new AuthResponse( token );
        String message = createCustomMessage( authRequest.getUsername( ) , "register" );
        System.out.println( message );
        return ResponseEntity.status( HttpStatus.CREATED ).body( authResponse );
    }
}