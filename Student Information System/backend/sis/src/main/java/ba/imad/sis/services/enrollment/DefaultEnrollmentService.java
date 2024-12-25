package ba.imad.sis.services.enrollment;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.repositories.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultEnrollmentService implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public DefaultEnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findEnrollmentsByStudentId(studentId).orElse(Collections.emptyList());
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findEnrollmentsByCourseId(courseId).orElse(Collections.emptyList());
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {
        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(enrollment.getStudent().getId(), enrollment.getCourse().getId());

        if(!exists) {
            enrollmentRepository.save(enrollment);
        } else {
            throw new IllegalArgumentException("Enrollment for this course already exists");
        }
    }

    @Override
    public void deleteEnrollmentById(Long id) {
        boolean exists = enrollmentRepository.existsById(id);

        if(!exists) {
            throw new IllegalArgumentException("Enrollment for this course does not exist");
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateEnrollment(Enrollment newEnrollment) {
        Enrollment existingEnrollment = enrollmentRepository.findById(newEnrollment.getId()).orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        if(newEnrollment.getPoints() != null &&
                !newEnrollment.getPoints().equals(existingEnrollment.getPoints())) {
            existingEnrollment.setPoints(newEnrollment.getPoints());
        }

        if(newEnrollment.getGrade() != null &&
                !newEnrollment.getGrade().equals(existingEnrollment.getGrade())) {
            existingEnrollment.setGrade(newEnrollment.getGrade());
        }
    }
}
