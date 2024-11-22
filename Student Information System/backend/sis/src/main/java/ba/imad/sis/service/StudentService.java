package ba.imad.sis.service;

import ba.imad.sis.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void createStudent(Student student);
    Student getStudentByIndex(String index);
    void deleteStudentByIndex(String index);
    void updateStudentByIndex(String index, Student student);
}
