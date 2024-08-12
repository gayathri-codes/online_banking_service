package com.effmobile.securityconfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtService {
	private String secret="+D1Z2vSvtjM9xgVwpZ8FztOVPX2ikYKEmS/ym0K0U6k=";
	private long expiration= 7200000;
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
		
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
		
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
       String token=createToken(claims, userDetails.getUsername());
        System.out.println("Generated Token: " + token); // Debug statement
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
        		.setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    
    
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
