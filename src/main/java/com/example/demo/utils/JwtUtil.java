package com.example.demo.utils;

import com.example.demo.config.MyUserDetails;
import io.jsonwebtoken.*;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    private String jwtSecret = "secretKey";

    private final int jwtExpiration = 86400000;

    public String generateToken(Authentication auth){

        MyUserDetails userPrincipal = (MyUserDetails) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            log.error("Invalid token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            log.error("Invalid token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
