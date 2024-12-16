package kr.ac.kumoh.ce.s20180260.crudtest.login.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "n8s3!A@zT6b#Y^mK&dXpQr*J1w2Lc7v!e9Df5P0ZGhR4o%B";  // 비밀키 (적절한 길이로 수정)

    // JWT 토큰 생성 메서드
    public String generateToken(String username, Long userid) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());  // 비밀키를 바이트 배열로 변환
        return Jwts.builder()
                .setSubject(username)
                .claim("userid", userid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간 유효
                .signWith(key)
                .compact();
    }


    // JWT 토큰에서 사용자명 추출 메서드
    public String extractUsername(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);  // "Bearer " 부분을 제거
        }
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return null;
        /*        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        try{
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return null;*/
    }

    // JWT 토큰에서 사용자 ID 추출
    public Long extractUserid(String token) {
        /*try{
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return claims.get("userid", Long.class);
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return Long.parseLong("0");*/
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);  // "Bearer " 부분을 제거
        }
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userid", Long.class);
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return Long.parseLong("0");
    }

    // JWT 토큰 검증 메서드
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // JWT 토큰 만료 여부 확인
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}