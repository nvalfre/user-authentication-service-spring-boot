package com.nv.userauthenticationservicespringboot.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET = "$2y$10$74PGhWt8IuLBFezzef4xN.WTRW6WEhLwTrWCSBSHWMjRmC3MuRQr.";
    private static final Long ACCESS_TOKEN_VALITY_SECONDS = 2592000L;

    public static String createToken(String name, String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + getExpirationTime());

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(payload)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuth(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }

    private static long getExpirationTime() {
        return ACCESS_TOKEN_VALITY_SECONDS * 1000L;
    }
}
