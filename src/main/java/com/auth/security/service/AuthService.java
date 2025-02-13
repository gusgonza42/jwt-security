package com.auth.security.service;

import com.auth.security.dto.AuthRequest;
import com.auth.security.dto.AuthResponse;
import com.auth.security.model.User;
import com.auth.security.repository.UserRepository;
import com.auth.security.util.AuthConstants;
import com.auth.security.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.auth.security.util.Utils.printMssg;


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
        printMssg( AuthConstants.HELLO_FROM_AUTH_PROJECT );
        return ResponseEntity.status( HttpStatus.OK ).body( AuthConstants.HELLO_FROM_AUTH_PROJECT );
    }

    public ResponseEntity< AuthResponse > login( AuthRequest authRequest ) {
        try {
            if ( authRequest.getUsername( ) == null && authRequest.getEmail( ) == null && authRequest.getPassword( ) == null ) {
                return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( new AuthResponse( null , AuthConstants.CREDENTIALS_REQUIRED ) );
            }
            User user = userRepository.findByUsername( authRequest.getUsername( ) );
            if ( user == null ) {
                user = userRepository.findByEmail( authRequest.getEmail( ) );
            }
            if ( user == null ) {
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( new AuthResponse( null , AuthConstants.USER_NOT_EXISTS ) );
            }
            if ( ! user.getPassword( ).equals( authRequest.getPassword( ) ) ) {
                return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( new AuthResponse( null , AuthConstants.INVALID_CREDENTIALS ) );
            }

            String token = user.getToken( );
            if ( token == null || ! jwtTokenUtils.isValidToken( token ) ) {
                token = jwtTokenUtils.generateToken( user.getUsername( ) );
                user.setToken( token );
                printMssg( AuthConstants.TOKEN_CREATED_OR_UPDATED );
                userRepository.save( user );
            }

            printMssg( user.getUsername( ) + " logged in" );

            return ResponseEntity.status( HttpStatus.OK ).body( new AuthResponse( token , AuthConstants.USER_LOGGED_IN ) );
        } catch ( Exception e ) {
            printMssg( e.getMessage( ) );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( new AuthResponse( null , AuthConstants.INTERNAL_SERVER_ERROR ) );
        }
    }

    public ResponseEntity< AuthResponse > register( AuthRequest authRequest ) {
        User userByEmail = userRepository.findByEmail( authRequest.getEmail( ) );
        if ( userByEmail != null ) {
            return ResponseEntity.status( HttpStatus.CONFLICT )
                    .body( new AuthResponse( null , AuthConstants.EMAIL_ALREADY_EXISTS ) );
        }

        User userByUsername = userRepository.findByUsername( authRequest.getUsername( ) );
        if ( userByUsername != null ) {
            return ResponseEntity.status( HttpStatus.CONFLICT )
                    .body( new AuthResponse( null , AuthConstants.USERNAME_ALREADY_EXISTS ) );
        }


        String token = jwtTokenUtils.generateToken( authRequest.getUsername( ) );
        User user = new User( );
        user.setUsername( authRequest.getUsername( ) );
        user.setEmail( authRequest.getEmail( ) );
        user.setPassword( authRequest.getPassword( ) );
        user.setToken( token );
        userRepository.save( user );

        printMssg( user.getUsername( ) + " registered" );
        return ResponseEntity.status( HttpStatus.CREATED ).body( new AuthResponse(token, AuthConstants.USER_REGISTERED_SUCCESSFULLY));
    }
}