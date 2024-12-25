package ba.imad.sis.repositories;

import ba.imad.sis.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<List<Enrollment>> findEnrollmentsByStudentId(Long studentId);
    Optional<List<Enrollment>> findEnrollmentsByCourseId(Long courseId);
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    boolean existsById(Long id);
}
