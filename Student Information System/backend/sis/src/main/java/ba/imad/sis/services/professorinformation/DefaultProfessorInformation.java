package ba.imad.sis.services.professorinformation;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.dtos.ProfessorUpdateRequest;
import ba.imad.sis.repositories.ProfessorInformationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DefaultProfessorInformation implements ProfessorInformationService {
    private final ProfessorInformationRepository professorInformationRepository;

    public DefaultProfessorInformation(ProfessorInformationRepository professorInformationRepository) {
        this.professorInformationRepository = professorInformationRepository;
    }

    @Override
    public ProfessorInformation getProfessorInformation(Long professorId){
        return professorInformationRepository.findByProfessorId(professorId).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    @Override
    public ProfessorInformation saveProfessorInformation(ProfessorInformation professorInformation) {
        boolean exists = professorInformationRepository.existsById(professorInformation.getId());

        if (exists) {
            throw new IllegalArgumentException("ProfessorInformation with id " + professorInformation.getId() + " already exists");
        }

        return professorInformationRepository.save(professorInformation);
    }

    @Override
    public void deleteProfessorInformation(Long professorId) {
        boolean exists = professorInformationRepository.existsByProfessorId(professorId);

        if (!exists) {
            throw new IllegalArgumentException("ProfessorInformation with id " + professorId + " does not exist");
        }

        professorInformationRepository.deleteById(professorId);
    }

    @Override
    @Transactional
    public void updateProfessorInformation(Long professorId, ProfessorUpdateRequest professorUpdateRequest) {
        ProfessorInformation oldProfessorInformation = getProfessorInformation(professorId);

        oldProfessorInformation.setFirstName(professorUpdateRequest.firstName());
        oldProfessorInformation.setLastName(professorUpdateRequest.lastName());

        professorInformationRepository.save(oldProfessorInformation);
    }
}
