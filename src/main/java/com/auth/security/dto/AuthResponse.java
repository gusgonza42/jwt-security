package com.auth.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class AuthResponse {
    private String token;

    public AuthResponse( String token ) {
        this.token = token;
    }


}
