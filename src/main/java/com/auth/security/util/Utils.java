package com.auth.security.util;

public class Utils {

    public static String createCustomMessage( String username , String action ) {
        String emoji = action.equals( "login" ) ? "🔑" : "📝";
        String color = action.equals( "login" ) ? "\u001B[32m" : "\u001B[34m"; // Verde para login, azul para registro
        String reset = "\u001B[0m";
        return color + emoji + " Usuario " + username + " ha realizado la acción: " + action + " " + emoji + reset;
    }
}