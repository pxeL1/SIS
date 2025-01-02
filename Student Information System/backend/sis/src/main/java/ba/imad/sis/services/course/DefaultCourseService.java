package ba.imad.sis.services.course;

import ba.imad.sis.domain.Course;
import ba.imad.sis.dtos.CourseUpdateRequest;
import ba.imad.sis.repositories.CourseRepository;
import ba.imad.sis.repositories.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService  implements CourseService {

    private final CourseRepository courseRepository;

    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> getAllCourses(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return courseRepository.findAll(pageable);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Page<Course> getCoursesByProfessorId(Long id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return courseRepository.findAllByProfessorId(id, pageable).orElse(Page.empty(pageable));
    }

    @Override
    public Course saveCourse(Course course) {
        boolean exists = courseRepository.existsByName(course.getName());

        if (!exists) {
            courseRepository.save(course);
        } else {
            throw new IllegalStateException("Course with name " + course.getName() + "already exists");
        }

        return course;
    }

    @Override
    public void deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Course with id " + id + " does not exist");
        }

        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCourse(CourseUpdateRequest newCourse, Long courseId) {
        Course oldCourse = getCourseById(courseId);

        oldCourse.setName(newCourse.name());
        oldCourse.setDescription(newCourse.description());
        oldCourse.setProfessor(newCourse.professor());
        courseRepository.save(oldCourse);
    }

    @Override
    public void updateProfessorToNull(Long professorId) {
        boolean exists = courseRepository.existsByProfessorId(professorId);

        if (!exists) {
            throw new IllegalStateException("Course with professorId " + professorId + " does not exist");
        }

        //todo:update professor column after deleting a professor
    }
}
