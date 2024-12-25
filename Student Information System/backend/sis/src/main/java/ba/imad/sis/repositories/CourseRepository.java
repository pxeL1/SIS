package ba.imad.sis.repositories;

import ba.imad.sis.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
    Optional<Course> findByName(String name);
    Optional<List<Course>> getCoursesByProfessorId(Long professorId);
}
