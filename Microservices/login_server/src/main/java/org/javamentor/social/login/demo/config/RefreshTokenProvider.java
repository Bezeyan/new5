package org.javamentor.social.login.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.javamentor.social.login.demo.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@Log
public class RefreshTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationTimeOfRefresh}")
    private int expirationTimeOfRefresh;

    private final PasswordEncoder passwordEncoder;

    public RefreshTokenProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generateRefreshToken(String email, Role role) {

        String encodeEmail = passwordEncoder.encode(email);
        Claims claims = Jwts.claims().setSubject(encodeEmail);
        claims.put("role", role.getRoleName());

        Date date = Date.from(Instant.now().plusMillis(expirationTimeOfRefresh));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
