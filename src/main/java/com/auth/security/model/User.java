package com.auth.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Entidad que representa un usuario en el sistema.
 */
@Getter
@Setter
@Entity
public class User {
    public static int status_active = 1;
    public static int status_inactive = 0;
    public static int role_admin = 0;
    public static int role_caregiver = 1;
    public static int role_user = 2;

    /**
     * Identificador único del usuario.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String firstName;

    private String lastName;


    /**
     * Nombre de usuario.
     */
    private String username;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * El estado del usuario.
     * Valor por defecto: 1 = activo, 0 = inactivo
     */
    @Column ( name = "status" )
    private int status = status_active;


    @Column( name = "role" )
    private int role = role_user;

    @Temporal ( TemporalType.DATE )
    private Date createdAt;

    /**
     * Establece la fecha de registro al crear el objeto usuario.
     */
    @PrePersist
    protected void onCreate( ) {
        if( createdAt == null ) {
            createdAt = new Date( );
        }
    }
    /**
     * Token JWT del usuario.
     */
    private String token;
}