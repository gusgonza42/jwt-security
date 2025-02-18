package com.auth.security.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String username;

    @Email( message = "El correo electrónico debe ser válido" )
    @Pattern( regexp = "^[\\w-.]+@[\\w-.]+\\.[a-z]{2,}$", message = "El correo electrónico debe ser válido" )
    @Size( max = 50, message = "El correo electrónico no debe tener más de 50 caracteres" )
    private String email;

    @NotBlank( message = "La contraseña es obligatoria" )
    private String password;
}