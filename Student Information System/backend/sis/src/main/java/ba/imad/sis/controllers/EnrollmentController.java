package ba.imad.sis.controllers;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import ba.imad.sis.services.enrollment.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity getEnrollments(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments(pageNo, pageSize));
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity getEnrollmentsByStudentId(@PathVariable("studentId") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByStudentId(id, pageNo, pageSize));
    }

    @GetMapping(value = "/course/{courseId}")
    public ResponseEntity getEnrollmentsByCourseId(@PathVariable("courseId") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByCourseId(id, pageNo, pageSize));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveEnrollment(@RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteEnrollment(@PathVariable("id") Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESSOR') or hasRole('DEMONSTRATOR')")
    public ResponseEntity updateEnrollment(@PathVariable("id") Long id, @RequestBody EnrollmentUpdateRequest enrollment) {
        try {
            enrollmentService.updateEnrollment(enrollment, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
