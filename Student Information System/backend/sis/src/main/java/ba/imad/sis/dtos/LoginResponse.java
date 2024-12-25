package ba.imad.sis.dtos;

import ba.imad.sis.domain.User;

public record LoginResponse(User user, String token) {
}
