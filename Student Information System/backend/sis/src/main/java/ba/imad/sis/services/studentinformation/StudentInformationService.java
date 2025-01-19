package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.StudentUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentInformationService {
    Page<StudentInformation> getAllStudentInformation(int pageNo, int pageSize);
    Page<StudentInformation> getFilteredStudentInformation(List<FilterDTO> filterDTO, int pageNo, int pageSize);
    StudentInformation getStudentInformation(Long studentId);
    void saveStudentInformation(StudentInformation studentInformation);
    void deleteStudentInformation(Long studentId);
    void updateStudentInformation(Long studentId, StudentUpdateRequest request);
}
