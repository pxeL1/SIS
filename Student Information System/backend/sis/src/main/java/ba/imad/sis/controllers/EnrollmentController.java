package ba.imad.sis.controllers;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import ba.imad.sis.services.enrollment.EnrollmentService;
import org.springframework.data.domain.Page;
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
    public Page<Enrollment> getEnrollments(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return enrollmentService.getAllEnrollments(pageNo, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/student/{studentId}")
    public Page<Enrollment> getEnrollmentsByStudentId(@PathVariable("studentId") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return enrollmentService.getAllEnrollmentsByStudentId(id, pageNo, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/course/{courseId}")
    public Page<Enrollment> getEnrollmentsByCourseId(@PathVariable("courseId") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return enrollmentService.getAllEnrollmentsByCourseId(id, pageNo, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public Enrollment saveEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    public void deleteEnrollment(@PathVariable("id") Long id) {
        enrollmentService.deleteEnrollment(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{id}")
    public void updateEnrollment(@PathVariable("id") Long id, @RequestBody EnrollmentUpdateRequest enrollment) {
        enrollmentService.updateEnrollment(enrollment, id);
    }
}
