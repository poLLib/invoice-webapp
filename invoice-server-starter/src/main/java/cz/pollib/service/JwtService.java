package cz.pollib.service;

import cz.pollib.config.JwtConfig.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey = Jwts.SIG.HS256.key()
                                                      .build();

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String extractUsername(String token) {
        return extractClaim(
                token,
                Claims::getSubject
                           );
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
                             ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(
                new HashMap<>(),
                userDetails
                            );
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
                               ) {
        return buildToken(
                extraClaims,
                userDetails,
                jwtProperties.expiration()
                         );
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
                             ) {
        return Jwts.builder()
                   .claims(extraClaims)
                   .subject(userDetails.getUsername())
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + expiration))
                   .signWith(getSignInKey())
                   .compact();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
                               ) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(
                token,
                Claims::getExpiration
                           );
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .verifyWith(getSignInKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    private SecretKey getSignInKey() {
        return secretKey;
    }
}
