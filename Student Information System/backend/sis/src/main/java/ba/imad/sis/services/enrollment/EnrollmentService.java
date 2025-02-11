package ba.imad.sis.services.enrollment;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import ba.imad.sis.dtos.FilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnrollmentService {
    Page<Enrollment> getAllEnrollments(Pageable pageable);
    Page<Enrollment> getAllEnrollmentsByStudentId(Long studentId, Pageable pageable);
    Page<Enrollment> getAllEnrollmentsByCourseId(Long courseId, Pageable pageable);
    Page<Enrollment> getFilteredEnrollments(List<FilterDTO> filterDTO, Pageable pageable);
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(Long id);
    void deleteAllStudentEnrollments(Long studentId);
    void deleteAllCourseEnrollments(Long courseId);
    void updateEnrollment(EnrollmentUpdateRequest enrollmentUpdateRequest, Long id);
}
