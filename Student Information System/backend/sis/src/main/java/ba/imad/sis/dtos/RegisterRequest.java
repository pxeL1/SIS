package ba.imad.sis.dtos;

public record RegisterRequest(String email, String password, String firstName, String lastName, String role, String index, Integer enrollmentYear) {
}
