package ba.imad.sis.controllers;

import ba.imad.sis.dtos.ProfessorUpdateRequest;
import ba.imad.sis.dtos.StudentUpdateRequest;
import ba.imad.sis.services.professorinformation.ProfessorInformationService;
import ba.imad.sis.services.studentinformation.StudentInformationService;
import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public void updatePassword(@RequestBody String password) {
        userService.updatePassword(userService.getCurrentUser(), password);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/student/{id}")
    @PreAuthorize("isAuthenticated()")
    public void updateStudent(@PathVariable("id") Long id, @RequestBody StudentUpdateRequest studentUpdateRequest) {
        studentInformationService.updateStudentInformation(id, studentUpdateRequest);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/professor/{id}")
    @PreAuthorize("isAuthenticated()")
    public void updateProfessor(@PathVariable("id") Long id, @RequestBody ProfessorUpdateRequest professorUpdateRequest) {
        professorInformationService.updateProfessorInformation(id, professorUpdateRequest);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
