package ba.imad.sis.services.course;

import ba.imad.sis.domain.Course;
import ba.imad.sis.repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultCourseService  implements CourseService {

    private final CourseRepository courseRepository;

    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getCoursesByProfessorId(Long id) {
        return courseRepository.getCoursesByProfessorId(id).orElse(Collections.emptyList());
    }

    @Override
    public void saveCourse(Course course) {
        boolean exists = courseRepository.existsByName(course.getName());

        if (!exists) {
            courseRepository.save(course);
        } else {
            throw new IllegalStateException("Course with name " + course.getName() + "already exists");
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Course with id " + id + " does not exist");
        }
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCourse(Course newCourse) {
        Course course = courseRepository.findById(newCourse.getId()).orElseThrow(() -> new IllegalStateException("Course with id " + newCourse.getId() + " does not exist"));

        if(newCourse.getName() != null &&
                !newCourse.getName().isEmpty() &&
                !newCourse.getName().equals(course.getName())) {
            course.setName(newCourse.getName());
        }

        if(newCourse.getDescription() != null &&
                !newCourse.getDescription().isEmpty() &&
                !newCourse.getDescription().equals(course.getDescription())) {
            course.setDescription(newCourse.getDescription());
        }

        if(newCourse.getProfessor() != null &&
                !Objects.equals(newCourse.getProfessor(), course.getProfessor())) {
            course.setProfessor(newCourse.getProfessor());
        }
    }
}
