package com.course.courseapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

@Value("${jwt.secret}")
private String SECRET_KEY; // Clé secrète à partir de la configuration
private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 heure

public String generateToken(String username) {
Claims claims = Jwts.claims().setSubject(username);
Date now = new Date();
Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
}

public boolean validateToken(String token) {
// Logic to validate token if necessary
return true;
}
}
