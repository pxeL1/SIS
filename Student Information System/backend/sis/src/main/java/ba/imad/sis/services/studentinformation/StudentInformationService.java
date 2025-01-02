package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.dtos.StudentUpdateRequest;

import java.util.List;

public interface StudentInformationService {
    List<StudentInformation> getAllStudentInformation();
    StudentInformation getStudentInformation(Long studentId);
    StudentInformation saveStudentInformation(StudentInformation studentInformation);
    void deleteStudentInformation(Long studentId);
    void updateStudentInformation(Long studentId, StudentUpdateRequest request);
}
