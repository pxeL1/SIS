package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;

import java.util.List;

public interface StudentInformationService {
    List<StudentInformation> getAllStudentInformation();
    StudentInformation getStudentInformation(Long studentId);
    void saveStudentInformation(StudentInformation studentInformation);
    void deleteStudentInformation(Long studentId);
    void updateStudentInformation(StudentInformation studentInformation);
}
