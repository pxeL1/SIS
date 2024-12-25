package ba.imad.sis.controllers;

import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.LoginRequest;
import ba.imad.sis.dtos.LoginResponse;
import ba.imad.sis.services.auth.JwtService;
import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DefaultUserService userService;

    public AuthenticationController(JwtService jwtService, AuthenticationManager authenticationManager, DefaultUserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
            User user = userService.loadUserByUsername(authentication.getName());
            String token = jwtService.createToken(user);
            return ResponseEntity.ok(new LoginResponse(user, token));
        }
        catch (AuthenticationException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }
}
