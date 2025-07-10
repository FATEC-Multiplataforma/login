package br.com.fatec.pokemon.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtSecurity {
    private static final String SECRET = "u7x!A$9zLq#2vNf@eR6tYpWmZcXbGdQh";
    private static final long EXP_MS = 2L * 60 * 1000;

    private byte[] getSecret() {
        return SECRET.getBytes();
    }

    public String generateToken(UserDetails user) {
        Date agora = new Date();
        SecretKey key = Keys.hmacShaKeyFor(getSecret());
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(agora)
                .expiration(new Date(agora.getTime() + EXP_MS))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        String username = getUsername(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(getSecret());
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}