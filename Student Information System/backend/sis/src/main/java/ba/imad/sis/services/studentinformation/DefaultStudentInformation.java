package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.StudentUpdateRequest;
import ba.imad.sis.repositories.StudentInformationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStudentInformation implements StudentInformationService {
    private final StudentInformationRepository studentInformationRepository;

    public DefaultStudentInformation(StudentInformationRepository studentInformationRepository) {
        this.studentInformationRepository = studentInformationRepository;
    }

    @Override
    public List<StudentInformation> getAllStudentInformation() {
        return studentInformationRepository.findAll();
    }

    @Override
    public StudentInformation getStudentInformation(Long studentId){
        return studentInformationRepository.findByStudentId(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public StudentInformation saveStudentInformation(StudentInformation studentInformation) {
        boolean exists = studentInformationRepository.existsByStudentId(studentInformation.getStudent().getId());

        if(exists){
            throw new IllegalArgumentException("Student information already exists");
        }

        return studentInformationRepository.save(studentInformation);
    }

    @Override
    public void deleteStudentInformation(Long studentId) {
        boolean exists = studentInformationRepository.existsByStudentId(studentId);

        if(!exists){
            throw new IllegalArgumentException("Student information does not exist");
        }
        studentInformationRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudentInformation(Long studentId, StudentUpdateRequest studentUpdateRequest) {
        StudentInformation oldStudentInformation = getStudentInformation(studentId);

        oldStudentInformation.setFirstName(studentUpdateRequest.firstName());
        oldStudentInformation.setLastName(studentUpdateRequest.lastName());
        oldStudentInformation.setIndex(studentUpdateRequest.index());
        oldStudentInformation.setEnrollmentYear(studentUpdateRequest.enrollmentYear());

        studentInformationRepository.save(oldStudentInformation);
    }
}
