package ba.imad.sis.services.professorinformation;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.dtos.ProfessorUpdateRequest;

public interface ProfessorInformationService {
    ProfessorInformation getProfessorInformation(Long professorId);
    ProfessorInformation saveProfessorInformation(ProfessorInformation professorInformation);
    void deleteProfessorInformation(Long professorId);
    void updateProfessorInformation(Long id, ProfessorUpdateRequest professorUpdateRequest);
}
