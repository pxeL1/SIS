package ba.imad.sis.controllers;

import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.PasswordUpdateRequest;
import ba.imad.sis.dtos.ProfessorUpdateRequest;
import ba.imad.sis.dtos.StudentUpdateRequest;
import ba.imad.sis.services.professorinformation.ProfessorInformationService;
import ba.imad.sis.services.studentinformation.StudentInformationService;
import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        userService.updatePassword(userService.getCurrentUser(), passwordUpdateRequest.password());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentInformationService.getAllStudentInformation(pageable));
    }

    @GetMapping(value = "/student/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentInformationService.getStudentInformation(id));
    }

    @PostMapping(value = "/student/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAllFilteredStudents(@RequestBody List<FilterDTO> filterDTO, @RequestBody Pageable pageable) {
        return ResponseEntity.ok(studentInformationService.getFilteredStudentInformation(filterDTO, pageable));
    }

    @GetMapping(value = "/professor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAllProfessors(Pageable pageable) {
        return ResponseEntity.ok(professorInformationService.getAllProfessorInformation(pageable));
    }

    @GetMapping(value = "/professor/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getProfessor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(professorInformationService.getProfessorInformation(id));
    }

    @PostMapping(value = "/professor/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAllFilteredProfessors(@RequestBody List<FilterDTO> filterDTO, @RequestBody Pageable pageable) {
        return ResponseEntity.ok(professorInformationService.getFilteredProfessorInformation(filterDTO, pageable));
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
