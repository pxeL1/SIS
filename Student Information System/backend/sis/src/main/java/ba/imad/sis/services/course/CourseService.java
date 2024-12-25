package ba.imad.sis.services.course;

import ba.imad.sis.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    List<Course> getCoursesByProfessorId(Long id);
    void saveCourse(Course course);
    void deleteCourseById(Long id);
    void updateCourse(Course newCourse);
}
