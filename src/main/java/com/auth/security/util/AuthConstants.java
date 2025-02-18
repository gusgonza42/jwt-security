package com.auth.security.util;

public class AuthConstants {


    public AuthConstants( ) {
        throw new IllegalStateException( "Utility class" );
    }

    public static final String HELLO_FROM_AUTH_PROJECT = "🔐 Hello from Auth Project!\n🚀 This endpoint doesn't require a token.\n🔑 Remember: all other endpoints require a valid token, except for login and register. 📝";

    public static final String USER_NOT_EXISTS = "⚠️ The user does not exist. Please try again with a different username or email.";
    public static final String CREDENTIALS_REQUIRED = "⚠️ Username, email, and password are required.";
    public static final String INVALID_CREDENTIALS = "🚫 Invalid username, email, or password. Please try again.";
    public static final String TOKEN_CREATED_OR_UPDATED = "✅ Token has been created or updated successfully!";
    public static final String INTERNAL_SERVER_ERROR = "⚠️ An unexpected error occurred. Please try again later.";

    public static final String EMAIL_ALREADY_EXISTS = "⚠️ The email is already in use. Please try again with a different email.";
    public static final String USERNAME_ALREADY_EXISTS = "⚠️ The username is already in use. Please try again with a different username.";

}
