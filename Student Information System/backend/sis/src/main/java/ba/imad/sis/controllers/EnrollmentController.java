package ba.imad.sis.controllers;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.services.enrollment.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Enrollment> getEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable("studentId") Long id) {
        return enrollmentService.getEnrollmentsByStudentId(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourseId(@PathVariable("courseId") Long id) {
        return enrollmentService.getEnrollmentsByCourseId(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void saveEnrollment(@RequestBody Enrollment enrollment) {
        enrollmentService.saveEnrollment(enrollment);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    public void deleteEnrollment(@PathVariable("id") Long id) {
        enrollmentService.deleteEnrollmentById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping
    public void updateEnrollment(@RequestBody Enrollment enrollment) {
        enrollmentService.updateEnrollment(enrollment);
    }
}
