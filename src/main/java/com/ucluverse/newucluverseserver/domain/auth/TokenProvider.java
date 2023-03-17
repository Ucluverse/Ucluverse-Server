package com.ucluverse.newucluverseserver.domain.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

@Configuration
@AllArgsConstructor
public class TokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.token-validity-in-seconds}")
    private int jwtExpirationInMs;
    private final Key key;

    public TokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex){
            throw new APIException("Invalid JWT signature", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException ex) {
            throw new APIException("Invalid JWT token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            throw new APIException("Expired JWT token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException ex) {
            throw new APIException("Unsupported JWT token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException ex) {
            throw new APIException("JWT claims string is empty.", HttpStatus.BAD_REQUEST);
        }
    }
}