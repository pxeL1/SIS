package ba.imad.sis.repositories;

import ba.imad.sis.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
    boolean existsByProfessorId(Long professorId);
    Optional<Page<Course>> findAllByProfessorId(Long professorId, Pageable pageable);
}
