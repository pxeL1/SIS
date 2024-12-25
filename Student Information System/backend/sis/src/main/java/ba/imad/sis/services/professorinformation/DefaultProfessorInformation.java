package ba.imad.sis.services.professorinformation;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.repositories.ProfessorInformationRepository;

public class DefaultProfessorInformation implements ProfessorInformationService {
    private final ProfessorInformationRepository professorInformationRepository;

    public DefaultProfessorInformation(ProfessorInformationRepository professorInformationRepository) {
        this.professorInformationRepository = professorInformationRepository;
    }

    @Override
    public ProfessorInformation getProfessorInformation(Long professorId){
        return professorInformationRepository.findByProfessorId(professorId).orElse(null);
    }
}
