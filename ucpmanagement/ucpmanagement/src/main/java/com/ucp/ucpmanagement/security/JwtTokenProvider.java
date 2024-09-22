
package com.ucp.ucpmanagement.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey jwtSecret = Keys.hmacShaKeyFor("524865259524865259524865259524865259".getBytes()); // Use a secure key
    private final int jwtExpirationInMs = 3600000; // 1 hour

    // Generate JWT Token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(jwtSecret)  // Use SecretKey instead of String
            .compact();
    }

    // Extract username from the JWT
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()  // Use parserBuilder instead of parser
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()  // Use parserBuilder instead of parser
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    

}
