package ba.imad.sis.services.course;

import ba.imad.sis.domain.Course;
import ba.imad.sis.dtos.CourseUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Page<Course> getAllCourses(Pageable pageable);
    Course getCourseById(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    void updateCourse(CourseUpdateRequest newCourse, Long courseId);
}
