package ba.imad.sis.repositories;

import ba.imad.sis.domain.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Page<Enrollment>> findAllByStudentId(Long studentId, Pageable pageable);
    Optional<Page<Enrollment>> findAllByCourseId(Long courseId, Pageable pageable);
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    boolean existsById(Long id);
    boolean existsByStudentId(Long studentId);
    boolean existsByCourseId(Long courseId);
    void deleteAllByStudentId(Long studentId);
    void deleteAllByCourseId(Long courseId);
}
