package ba.imad.sis.repositories;

import ba.imad.sis.domain.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StudentInformationRepository extends JpaRepository<StudentInformation, Long>, JpaSpecificationExecutor<StudentInformation> {
    Optional<StudentInformation> findByStudentId(Long studentId);
    boolean existsByStudentId(Long studentId);
}
