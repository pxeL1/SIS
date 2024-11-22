package ba.imad.sis.controllers;

import ba.imad.sis.domain.Student;
import ba.imad.sis.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{index}")
    public Student getStudentByIndex(@PathVariable String index) {
        return studentService.getStudentByIndex(index);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{index}")
    public void deleteStudent(@PathVariable String index) {
        studentService.deleteStudentByIndex(index);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{index}")
    public void updateStudent(@RequestBody Student student, @PathVariable String index) {
        studentService.updateStudentByIndex(index, student);
    }
}
