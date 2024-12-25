package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;

public interface StudentInformationService {
    StudentInformation getStudentInformation(Long studentId);
}
