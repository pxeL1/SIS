package ba.imad.sis.dtos;

import ba.imad.sis.domain.Role;

import java.util.List;

public record RegisterRequest(String email, String password, String firstName, String lastName, Role role, String index, Integer enrollmentYear) {
}
