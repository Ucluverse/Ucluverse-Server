package com.ucluverse.newucluverseserver.domain.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.Authentication;


import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Configuration
public class TokenProvider {

    private String secretKey = "kwondaehwi";
    private int accessTokenValidityInSeconds = 600;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createJWT(String user_email, String user_id, String user_role) {
        Claims claims = Jwts.claims().setSubject(user_id); // JWT payload 에 저장되는 정보단위
        claims.put("email", user_email); // 정보는 key / value 쌍으로 저장된다.
        claims.put("role", user_role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidityInSeconds)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


}