package ba.imad.sis.controllers;

import ba.imad.sis.dtos.LoginRequest;
import ba.imad.sis.dtos.RegisterRequest;
import ba.imad.sis.services.auth.LoginService;
import ba.imad.sis.services.auth.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final LoginService loginService;
    private final RegisterService registerService;
    public AuthenticationController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try{
            return ResponseEntity.ok(loginService.loginUser(loginRequest));
        } catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        try{
            return ResponseEntity.ok(registerService.registerUser(registerRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
