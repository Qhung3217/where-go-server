package com.wherego.wheregoserver.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {
    String extractUsername(String token);
    <T>T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    List<GrantedAuthority> extractAuthorities(String token);
}
