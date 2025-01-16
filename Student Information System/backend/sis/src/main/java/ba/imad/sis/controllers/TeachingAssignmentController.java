package ba.imad.sis.controllers;

import ba.imad.sis.domain.TeachingAssignment;
import ba.imad.sis.dtos.TeachingAssignmentUpdateRequest;
import ba.imad.sis.services.teachingassignment.TeachingAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teaching")
public class TeachingAssignmentController {
    private final TeachingAssignmentService teachingAssignmentService;

    public TeachingAssignmentController(TeachingAssignmentService teachingAssignmentService) {
        this.teachingAssignmentService = teachingAssignmentService;
    }

    @GetMapping
    public ResponseEntity getAllTeachingAssignments(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(teachingAssignmentService.getTeachingAssignments(pageNo, pageSize));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTeachingAssignmentById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(teachingAssignmentService.getTeachingAssignmentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/professor/{id}")
    public ResponseEntity getTeachingAssignmentByProfessorId(@PathVariable("id") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(teachingAssignmentService.getTeachingAssignmentsByProffessorId(id, pageNo, pageSize));
    }

    @GetMapping(value = "/course/{id}")
    public ResponseEntity getTeachingAssignmentsByCourseId(@PathVariable("id") Long id, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(teachingAssignmentService.getTeachingAssignmentsByCourseId(id, pageNo, pageSize));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveTeachingAssignment(@RequestBody TeachingAssignment teachingAssignment) {
        try {
            return ResponseEntity.ok(teachingAssignmentService.saveTeachingAssignment(teachingAssignment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTeachingAssignment(@PathVariable("id") Long id) {
        try {
            teachingAssignmentService.deleteTeachingAssignmentById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateTeachingAssignment(@RequestBody TeachingAssignmentUpdateRequest teachingAssignmentUpdateRequest, @PathVariable("id") Long id) {
        try {
            teachingAssignmentService.updateTeachingAssignment(teachingAssignmentUpdateRequest, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
