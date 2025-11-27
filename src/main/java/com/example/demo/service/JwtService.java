package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final String jwtSecret = "superhemloignyckelsomärlång1234567890";

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims = new HashMap<>();

        claims.put("roles", userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    private Claims extractAllClaims(String token){

        return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String extractUserName(String token){
        return extractAllClaims(token).getSubject();
    }

    public List<String>  extractRoles(String token){
        return extractAllClaims(token).get("roles", List.class);
    }
}


