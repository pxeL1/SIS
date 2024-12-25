package ba.imad.sis.services.enrollment;

import ba.imad.sis.domain.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    List<Enrollment> getEnrollmentsByStudentId(Long studentId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    void saveEnrollment(Enrollment enrollment);
    void deleteEnrollmentById(Long id);
    void updateEnrollment(Enrollment newEnrollment);
}
