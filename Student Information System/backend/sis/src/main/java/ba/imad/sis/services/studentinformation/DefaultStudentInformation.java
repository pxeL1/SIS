package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.repositories.StudentInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentInformation implements StudentInformationService {
    private final StudentInformationRepository studentInformationRepository;

    public DefaultStudentInformation(StudentInformationRepository studentInformationRepository) {
        this.studentInformationRepository = studentInformationRepository;
    }

    @Override
    public StudentInformation getStudentInformation(Long studentId){
        return studentInformationRepository.findByStudentId(studentId).orElse(null);
    }
}
