package ba.imad.sis.services.enrollment;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import org.springframework.data.domain.Page;

public interface EnrollmentService {
    Page<Enrollment> getAllEnrollments(int pageNo, int pageSize);
    Page<Enrollment> getAllEnrollmentsByStudentId(Long studentId, int pageNo, int pageSize);
    Page<Enrollment> getAllEnrollmentsByCourseId(Long courseId, int pageNo, int pageSize);
    Page<Enrollment> getFilteredEnrollments(int grade, int pageNo, int pageSize);
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(Long id);
    void deleteAllStudentEnrollments(Long studentId);
    void deleteAllCourseEnrollments(Long courseId);
    void updateEnrollment(EnrollmentUpdateRequest enrollmentUpdateRequest, Long id);
}
