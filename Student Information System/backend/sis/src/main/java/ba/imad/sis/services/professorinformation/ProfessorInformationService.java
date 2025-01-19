package ba.imad.sis.services.professorinformation;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.ProfessorUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorInformationService {
    Page<ProfessorInformation> getAllProfessorInformation(int pageNo, int pageSize);
    Page<ProfessorInformation> getFilteredProfessorInformation(List<FilterDTO> filterDTO, int pageNo, int pageSize);
    ProfessorInformation getProfessorInformation(Long professorId);
    void saveProfessorInformation(ProfessorInformation professorInformation);
    void deleteProfessorInformation(Long professorId);
    void updateProfessorInformation(Long id, ProfessorUpdateRequest professorUpdateRequest);
}
