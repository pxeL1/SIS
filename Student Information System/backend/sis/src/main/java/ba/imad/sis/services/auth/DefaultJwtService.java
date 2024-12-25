package ba.imad.sis.services.auth;

import ba.imad.sis.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class DefaultJwtService implements JwtService {
    @Value("${config.secret-key}")
    private String SECRET_KEY;
    private static final Duration EXPIRATION_TIME = Duration.ofMinutes(60);

    public DefaultJwtService() {
    }

    @Override
    public String createToken(User user){
        Claims claims = Jwts.claims().subject(user.getEmail()).build();
        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(Instant.now().plus(EXPIRATION_TIME)))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public Claims resolveClaims(String token) throws JwtException {
        if(token != null){
            return Jwts.parser()
                    .verifyWith((SecretKey) getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

        return null;
    }

    @Override
    public String extractToken(String bearerToken) {
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    public boolean validateExpiration(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    @Override
    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
