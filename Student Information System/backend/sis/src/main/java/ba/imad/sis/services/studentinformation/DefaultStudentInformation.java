package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
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
        return studentInformationRepository.findByStudentId(studentId).orElse(null);
    }

    @Override
    public void saveStudentInformation(StudentInformation studentInformation) {
        boolean exists = studentInformationRepository.existsByStudentId(studentInformation.getStudent().getId());

        if(!exists){
            studentInformationRepository.save(studentInformation);
        }
        else {
            throw new IllegalArgumentException("Student information already exists");
        }
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
    public void updateStudentInformation(StudentInformation newStudentInformation) {
        StudentInformation oldStudentInformation = getStudentInformation(newStudentInformation.getStudent().getId());

        oldStudentInformation.setFirstName(newStudentInformation.getFirstName());
        oldStudentInformation.setLastName(newStudentInformation.getLastName());
        oldStudentInformation.setIndex(newStudentInformation.getIndex());
        oldStudentInformation.setEnrollmentYear(newStudentInformation.getEnrollmentYear());
        oldStudentInformation.setStudent(newStudentInformation.getStudent());
        studentInformationRepository.save(oldStudentInformation);
    }
}
