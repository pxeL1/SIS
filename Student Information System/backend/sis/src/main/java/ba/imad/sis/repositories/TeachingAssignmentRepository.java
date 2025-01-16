package ba.imad.sis.repositories;

import ba.imad.sis.domain.TeachingAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, Long> {
    Optional<Page<TeachingAssignment>> findAllByProfessorId(Long professorId, Pageable pageable);
    Optional<Page<TeachingAssignment>> findAllByCourseId(Long courseId, Pageable pageable);
    boolean existsByProfessorIdAndCourseId(Long professorId, Long courseId);
    boolean existsByProfessorId(Long professorId);
    boolean existsByCourseId(Long courseId);
    void deleteAllByProfessorId(Long professorId);
    void deleteAllByCourseId(Long courseId);
}
