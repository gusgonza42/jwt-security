package com.auth.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse( String token ) {
        this.token = token;
    }



}
