package ba.imad.sis.services.course;

import ba.imad.sis.domain.Course;
import ba.imad.sis.dtos.CourseUpdateRequest;
import ba.imad.sis.repositories.CourseRepository;
import ba.imad.sis.services.enrollment.EnrollmentService;
import ba.imad.sis.services.teachingassignment.TeachingAssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService  implements CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentService enrollmentService;
    private final TeachingAssignmentService teachingAssignmentService;

    public DefaultCourseService(CourseRepository courseRepository, EnrollmentService enrollmentService, TeachingAssignmentService teachingAssignmentService) {
        this.courseRepository = courseRepository;
        this.enrollmentService = enrollmentService;
        this.teachingAssignmentService = teachingAssignmentService;
    }

    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course saveCourse(Course course) {
        boolean exists = courseRepository.existsByName(course.getName());

        if (!exists) {
            courseRepository.save(course);
        } else {
            throw new IllegalStateException("Course with name " + course.getName() + " already exists");
        }

        return course;
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Course with id " + id + " does not exist");
        }

        teachingAssignmentService.deleteAllCourseTeachingAssignments(id);
        enrollmentService.deleteAllCourseEnrollments(id);

        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCourse(CourseUpdateRequest newCourse, Long courseId) {
        Course oldCourse = getCourseById(courseId);

        oldCourse.setName(newCourse.name());
        oldCourse.setDescription(newCourse.description());
        courseRepository.save(oldCourse);
    }
}
