package ba.imad.sis.controllers;

import ba.imad.sis.domain.Enrollment;
import ba.imad.sis.dtos.EnrollmentUpdateRequest;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.services.enrollment.EnrollmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity getEnrollments(Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments(pageable));
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity getEnrollmentsByStudentId(@PathVariable("studentId") Long id, Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByStudentId(id, pageable));
    }

    @GetMapping(value = "/course/{courseId}")
    public ResponseEntity getEnrollmentsByCourseId(@PathVariable("courseId") Long id, Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByCourseId(id, pageable));
    }

    @PostMapping(value = "/filter")
    public ResponseEntity getFilteredEnrollments(@RequestBody List<FilterDTO> filterDTO, Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getFilteredEnrollments(filterDTO, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity saveEnrollment(@RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteEnrollment(@PathVariable("id") Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('PROFESSOR') or hasAuthority('DEMONSTRATOR')")
    public ResponseEntity updateEnrollment(@PathVariable("id") Long id, @RequestBody EnrollmentUpdateRequest enrollment) {
        try {
            enrollmentService.updateEnrollment(enrollment, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
