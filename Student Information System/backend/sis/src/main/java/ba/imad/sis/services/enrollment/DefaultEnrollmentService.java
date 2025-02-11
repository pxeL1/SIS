package ba.imad.sis.services.enrollment;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.repositories.EnrollmentRepository;
import ba.imad.sis.specifications.FilterSpecifications;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultEnrollmentService implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public DefaultEnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Page<Enrollment> getAllEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }

    @Override
    public Page<Enrollment> getAllEnrollmentsByStudentId(Long studentId, Pageable pageable) {
        return enrollmentRepository.findAllByStudentId(studentId, pageable).orElse(Page.empty(pageable));
    }

    @Override
    public Page<Enrollment> getAllEnrollmentsByCourseId(Long courseId, Pageable pageable) {
        return enrollmentRepository.findAllByCourseId(courseId, pageable).orElse(Page.empty(pageable));
    }

    @Override
    public Page<Enrollment> getFilteredEnrollments(List<FilterDTO> filterDTO, Pageable pageable) {
        return enrollmentRepository.findAll(FilterSpecifications.columnEquals(filterDTO), pageable);
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(enrollment.getStudent().getId(), enrollment.getCourse().getId());

        if(!exists) {
            enrollmentRepository.save(enrollment);
        }
        else {
            throw new IllegalArgumentException("Enrollment for this course already exists");
        }

        return enrollment;
    }

    @Override
    public void deleteEnrollment(Long id) {
        boolean exists = enrollmentRepository.existsById(id);

        if(!exists) {
            throw new IllegalArgumentException("Enrollment for this course does not exist");
        }

        enrollmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllStudentEnrollments(Long studentId) {
        boolean exists = enrollmentRepository.existsByStudentId(studentId);

        if(!exists) {
            throw new IllegalArgumentException("Enrollment for this student does not exist");
        }

        enrollmentRepository.deleteAllByStudentId(studentId);
    }

    @Override
    @Transactional
    public void deleteAllCourseEnrollments(Long courseId) {
        boolean exists = enrollmentRepository.existsByCourseId(courseId);

        if(!exists) {
            throw new IllegalArgumentException("Enrollment for this course does not exist");
        }

        enrollmentRepository.deleteAllByCourseId(courseId);
    }

    @Override
    @Transactional
    public void updateEnrollment(EnrollmentUpdateRequest enrollmentUpdateRequest, Long id) {
        Enrollment oldEnrollment = enrollmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        oldEnrollment.setPoints(enrollmentUpdateRequest.points());
        oldEnrollment.setGrade(enrollmentUpdateRequest.grade());
        oldEnrollment.setPassed(enrollmentUpdateRequest.passed());

        enrollmentRepository.save(oldEnrollment);
    }
}
