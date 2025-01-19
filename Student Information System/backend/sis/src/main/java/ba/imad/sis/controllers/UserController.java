package ba.imad.sis.controllers;

import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.ProfessorUpdateRequest;
import ba.imad.sis.dtos.StudentUpdateRequest;
import ba.imad.sis.services.professorinformation.ProfessorInformationService;
import ba.imad.sis.services.studentinformation.StudentInformationService;
import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final DefaultUserService userService;
    private final StudentInformationService studentInformationService;
    private final ProfessorInformationService professorInformationService;

    public UserController(DefaultUserService userService, StudentInformationService studentInformationService, ProfessorInformationService professorInformationService) {
        this.userService = userService;
        this.studentInformationService = studentInformationService;
        this.professorInformationService = professorInformationService;
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updatePassword(@RequestBody String password) {
        userService.updatePassword(userService.getCurrentUser(), password);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllStudents(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(studentInformationService.getAllStudentInformation(pageNo, pageSize));
    }

    @GetMapping(value = "/student/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllFilteredStudents(@RequestBody List<FilterDTO> filterDTO, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(studentInformationService.getFilteredStudentInformation(filterDTO, pageNo, pageSize));
    }

    @GetMapping(value = "/professor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllProfessors(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(professorInformationService.getAllProfessorInformation(pageNo, pageSize));
    }

    @GetMapping(value = "/professor/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllFilteredProfessors(@RequestBody List<FilterDTO> filterDTO, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(professorInformationService.getFilteredProfessorInformation(filterDTO, pageNo, pageSize));
    }

    @PutMapping(value = "/student/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateStudent(@PathVariable("id") Long id, @RequestBody StudentUpdateRequest studentUpdateRequest) {
        try {
            studentInformationService.updateStudentInformation(id, studentUpdateRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/professor/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateProfessor(@PathVariable("id") Long id, @RequestBody ProfessorUpdateRequest professorUpdateRequest) {
        try {
            professorInformationService.updateProfessorInformation(id, professorUpdateRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
