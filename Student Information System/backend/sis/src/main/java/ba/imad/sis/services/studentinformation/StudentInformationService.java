package ba.imad.sis.services.studentinformation;

import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.StudentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentInformationService {
    Page<StudentInformation> getAllStudentInformation(Pageable pageable);
    Page<StudentInformation> getFilteredStudentInformation(List<FilterDTO> filterDTO, Pageable pageable);
    StudentInformation getStudentInformation(Long studentId);
    void saveStudentInformation(StudentInformation studentInformation);
    void deleteStudentInformation(Long studentId);
    void updateStudentInformation(Long studentId, StudentUpdateRequest request);
}
