package ba.imad.sis.config;

import ba.imad.sis.services.auth.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String bearerToken = request.getHeader("Authorization");
            String token = jwtService.extractToken(bearerToken);

            if(token == null){
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtService.resolveClaims(token);

            if(claims != null && jwtService.validateExpiration(claims)){
                String email = claims.getSubject();
                String role = claims.get("role", String.class);
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(email, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e){
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
        filterChain.doFilter(request, response);
    }
}
