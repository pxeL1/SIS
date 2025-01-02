package ba.imad.sis.dtos;

import ba.imad.sis.domain.User;

public record CourseUpdateRequest(String name, String description, User professor) {
}
