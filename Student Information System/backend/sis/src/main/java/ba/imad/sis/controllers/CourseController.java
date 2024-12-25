package ba.imad.sis.controllers;

import ba.imad.sis.domain.Course;
import ba.imad.sis.services.course.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getCourseById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{professorId}")
    public List<Course> getCoursesByProfessorId(@PathVariable("professorId") Long id) {
        return courseService.getCoursesByProfessorId(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void saveCourse(@RequestBody Course course) {
        courseService.saveCourse(course);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    public void deleteCourseById(@PathVariable("id") Long id) {
        courseService.deleteCourseById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping
    public void updateCourseById(@RequestBody Course course) {
        courseService.updateCourse(course);
    }
}
