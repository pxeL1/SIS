package ba.imad.sis.services.auth;

import ba.imad.sis.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.util.function.Function;

public interface JwtService {
    String createToken(User user);
    Claims resolveClaims(String token) throws JwtException;
    String extractToken(String bearerToken);
    boolean validateExpiration(Claims claims);
    String getEmail(Claims claims);
}
