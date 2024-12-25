package ba.imad.sis.repositories;

import ba.imad.sis.domain.ProfessorInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorInformationRepository extends JpaRepository<ProfessorInformation, Long> {
    Optional<ProfessorInformation> findByProfessorId(Long professorId);
}
