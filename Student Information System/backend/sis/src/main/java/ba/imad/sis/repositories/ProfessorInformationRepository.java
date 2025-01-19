package ba.imad.sis.repositories;

import ba.imad.sis.domain.ProfessorInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProfessorInformationRepository extends JpaRepository<ProfessorInformation, Long>, JpaSpecificationExecutor<ProfessorInformation> {
    Optional<ProfessorInformation> findByProfessorId(Long professorId);
    boolean existsByProfessorId(Long professorId);
}
