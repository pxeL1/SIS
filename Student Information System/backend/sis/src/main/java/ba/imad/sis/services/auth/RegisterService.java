package ba.imad.sis.services.auth;

import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.RegisterRequest;

public interface RegisterService {
    User registerUser(RegisterRequest registerRequest);
}
