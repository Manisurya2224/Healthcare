package com.example.Healthcare.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        String roles = populateAuthorities(auth.getAuthorities());

        return Jwts.builder()
                .setSubject(auth.getName()) // email goes in subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .claim("authorities", roles)
                .signWith(key,io.jsonwebtoken.SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromJwtToken(String jwt) {
        jwt = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject(); // use subject as email
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> roles = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        return String.join(",", roles);
    }
}
