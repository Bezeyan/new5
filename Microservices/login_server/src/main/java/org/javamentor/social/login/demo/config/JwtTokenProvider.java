package org.javamentor.social.login.demo.config;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.javamentor.social.login.demo.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationTimeOfJwt}")
    private int expirationTimeOfJwt;

    public String generateJwtToken(String email, Role role, Long userId) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role.getRoleName());
        claims.put("id", userId);

        Date date = Date.from(Instant.now().plusMillis(expirationTimeOfJwt));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

}
