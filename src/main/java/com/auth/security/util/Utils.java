package com.auth.security.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public Utils( ) {
        throw new IllegalStateException( "Utility class" );
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    public static void printMssg( String message ) {
        String timestamp = LocalDateTime.now( ).format( formatter );
        String method = Thread.currentThread( ).getStackTrace( )[ 2 ].getMethodName( );
        String className = Thread.currentThread( ).getStackTrace( )[ 2 ].getClassName( );

        // Códigos de escape ANSI para colores
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String purple = "\u001B[35m";
        String cyan = "\u001B[36m";

        System.out.println( blue + "🌌 ¡Operación exitosa! 🌟  O " + red + "💥 ERROR EN LA NAVE 🚨" + reset + " - \n" +
                "[" + yellow + timestamp + reset + " - " + cyan + className + reset + "." + green + method + reset + "]: \n\n" +
                purple + message + reset + "\n\n" +
                "🚀🛸  || 🚧🛠️" + "\n" +
                "- - - - - - - END PRINT MESSAGE - - - - - - - \n" );
    }


}