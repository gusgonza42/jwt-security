package com.auth.security.service;

    import com.auth0.jwt.JWT;
    import com.auth0.jwt.algorithms.Algorithm;
    import com.auth0.jwt.exceptions.JWTVerificationException;
    import com.auth0.jwt.interfaces.DecodedJWT;
    import com.auth0.jwt.interfaces.JWTVerifier;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.stereotype.Service;

    import java.util.Date;

    import org.springframework.security.core.userdetails.User;

    @Service
    public class JwtTokenService {
        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration}")
        private long jwtExpirationInMs;

        @Value("${jwt.refreshThreshold}")
        private long refreshThresholdInMs;

        public String generateToken(String username) {
            return JWT.create()
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                    .sign(Algorithm.HMAC256(secretKey));
        }

        public boolean isValidToken(String token) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                return jwt.getExpiresAt().after(new Date());
            } catch (JWTVerificationException e) {
                return false;
            }
        }

        public boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public boolean isTokenNearExpiry(String token) {
            return extractExpiration(token).before(new Date(System.currentTimeMillis() + refreshThresholdInMs));
        }

        public String refreshToken(String username) {
            return generateToken(username);
        }

        public UsernamePasswordAuthenticationToken getAuthentication(String token) {
            String username = extractUsername(token);
            User userDetails = new User(username, "", java.util.Collections.emptyList());
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        public String extractUsername( String token ) {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token)
                    .getSubject();
        }

        private Date extractExpiration(String token) {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token)
                    .getExpiresAt();
        }
    }