package com.spms.security.jwt;

import com.spms.exception.InvalidTokenException;
import com.spms.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String TOKEN_TYPE = "tokenType";
    private static final String ACCESS = "ACCESS";
    private static final String REFRESH = "REFRESH";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Short-lived token for API calls
    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(userDetails, accessExpiration, ACCESS);
    }

    // Long-lived token for getting new access token
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails, refreshExpiration, REFRESH);
    }

    private String buildToken(UserDetails userDetails, long expiration, String tokenType) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority())
                .claim(TOKEN_TYPE, tokenType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractTokenType(String token) {
        return extractClaim(token, claims -> claims.get(TOKEN_TYPE, String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException();

        } catch (JwtException ex) {
            throw new InvalidTokenException();
        }
    }

    // Validate access token on API requests
    public boolean isAccessTokenValid(String token, UserDetails userDetails) {
        return ACCESS.equals(extractTokenType(token))
                && usernameMatches(token, userDetails)
                && !isTokenExpired(token);
    }

    // Validate refresh token on /refresh endpoint
    public boolean isRefreshTokenValid(String token, UserDetails userDetails) {
        return REFRESH.equals(extractTokenType(token))
                && usernameMatches(token, userDetails)
                && !isTokenExpired(token);
    }

    private boolean usernameMatches(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}