package com.auth.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String message;

    public Message( String message ) {
        this.message = message;
    }
}
