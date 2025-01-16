package ba.imad.sis.services.auth;

import ba.imad.sis.dtos.LoginRequest;
import ba.imad.sis.dtos.LoginResponse;

public interface LoginService {
    LoginResponse loginUser(LoginRequest loginRequest);
}
