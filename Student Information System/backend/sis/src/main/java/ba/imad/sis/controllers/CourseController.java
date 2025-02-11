package ba.imad.sis.controllers;

import ba.imad.sis.domain.Course;
import ba.imad.sis.dtos.CourseUpdateRequest;
import ba.imad.sis.services.course.CourseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveCourse(@RequestBody Course course) {
        try{
            return ResponseEntity.ok(courseService.saveCourse(course));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteCourseById(@PathVariable("id") Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateCourseById(@RequestBody CourseUpdateRequest course, @PathVariable("id") Long id) {
        try {
            courseService.updateCourse(course, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
