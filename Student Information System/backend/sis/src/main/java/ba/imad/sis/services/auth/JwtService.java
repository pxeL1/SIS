package ba.imad.sis.services.auth;

import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.LoginRequest;
import ba.imad.sis.dtos.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.function.Function;

public interface JwtService {
    String createToken(User user);
    Claims resolveClaims(String token) throws JwtException;
    String extractToken(String bearerToken);
    boolean validateExpiration(Claims claims);
    boolean isTokenExpired(HttpServletRequest request);
}
