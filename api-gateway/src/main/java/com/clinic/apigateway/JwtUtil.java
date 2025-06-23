package com.clinic.apigateway;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class    JwtUtil {

    //private final String SECRET = "super-secret-key-super-secret-key"; // должен быть таким же, как в Auth-сервисе
    private final Key key = Keys.hmacShaKeyFor("super-secret-key-super-secret-key".getBytes(StandardCharsets.UTF_8));
    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
