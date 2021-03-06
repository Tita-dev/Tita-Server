package com.example.jwt.config.security.jwt;

import com.example.jwt.config.security.auth.MyUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

    public final static String ACCESS_TOKEN_NAME = "accessToken";
    public final static String REFRESH_TOKEN_NAME = "refreshToken";

    private final MyUserDetails myUserDetails;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException,IllegalArgumentException, MalformedJwtException, UnsupportedJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

//    public String getUserTokenType(String token){
//        return extractAllClaims(token).get("TokenType", String.class);
//    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String resolveToken(HttpServletRequest request){
        String beareToken = request.getHeader("Authorization");
        if(beareToken != null && beareToken.startsWith("Bearer "))
            return beareToken.substring(7);
        else
            return null;
    }

    public String generateToken(String username) {
        return doGenerateToken(username, TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(String username) {
        return doGenerateToken(username, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(String username, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("username", username);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + expireTime)))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public Long getExpiration(String accessToken){
        Date expiration = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(accessToken).getBody().getExpiration();
        return expiration.getTime();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
