package ba.imad.sis.controllers;

import ba.imad.sis.domain.Course;
import ba.imad.sis.dtos.CourseUpdateRequest;
import ba.imad.sis.services.course.CourseService;
import ba.imad.sis.services.enrollment.EnrollmentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public Page<Course> getAllCourses(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return courseService.getAllCourses(pageNo, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getCourseById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/professor/{professorId}")
    public Page<Course> getCoursesByProfessorId(@PathVariable("professorId") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return courseService.getCoursesByProfessorId(id, pageNo, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    public void deleteCourseById(@PathVariable("id") Long id) {
        enrollmentService.deleteAllCourseEnrollments(id);
        courseService.deleteCourse(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{id}")
    public void updateCourseById(@RequestBody CourseUpdateRequest course, @PathVariable("id") Long id) {
        courseService.updateCourse(course, id);
    }
}
