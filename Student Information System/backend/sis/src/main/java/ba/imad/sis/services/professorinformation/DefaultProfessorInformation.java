package ba.imad.sis.services.professorinformation;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.ProfessorUpdateRequest;
import ba.imad.sis.repositories.ProfessorInformationRepository;
import ba.imad.sis.specifications.ProfessorSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProfessorInformation implements ProfessorInformationService {
    private final ProfessorInformationRepository professorInformationRepository;

    public DefaultProfessorInformation(ProfessorInformationRepository professorInformationRepository) {
        this.professorInformationRepository = professorInformationRepository;
    }

    @Override
    public Page<ProfessorInformation> getAllProfessorInformation(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return professorInformationRepository.findAll(pageable);
    }

    @Override
    public Page<ProfessorInformation> getFilteredProfessorInformation(List<FilterDTO> filterDTO, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return professorInformationRepository.findAll(ProfessorSpecification.columnEqualsProfessor(filterDTO), pageable);
    }

    @Override
    public ProfessorInformation getProfessorInformation(Long professorId){
        return professorInformationRepository.findByProfessorId(professorId).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    @Override
    public void saveProfessorInformation(ProfessorInformation professorInformation) {
        boolean exists = professorInformationRepository.existsById(professorInformation.getId());

        if (exists) {
            throw new IllegalArgumentException("ProfessorInformation with id " + professorInformation.getId() + " already exists");
        }

        professorInformationRepository.save(professorInformation);
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
