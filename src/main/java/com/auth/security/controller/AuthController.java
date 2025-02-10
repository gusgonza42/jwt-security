package com.auth.security.controller;

import com.auth.security.dto.AuthRequest;
import com.auth.security.dto.AuthResponse;
import com.auth.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/auth" )
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController( AuthService authService ) {
        this.authService = authService;
    }

    @PostMapping( "/login" )
    public ResponseEntity< AuthResponse > login( @RequestBody AuthRequest authRequest ) {
        return authService.login( authRequest );
    }

    @PostMapping( "/register" )
    public ResponseEntity< AuthResponse > register( @RequestBody AuthRequest authRequest ) {
        return authService.register( authRequest );
    }
}