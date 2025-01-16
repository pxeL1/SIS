package ba.imad.sis.services.auth;

import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.LoginRequest;
import ba.imad.sis.dtos.LoginResponse;
import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DefaultLoginService implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final DefaultUserService userService;
    private final JwtService jwtService;

    public DefaultLoginService(AuthenticationManager authenticationManager, DefaultUserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        User user = userService.loadUserByUsername(authentication.getName());
        String token = jwtService.createToken(user);
        return new LoginResponse(user, token);
    }
}
