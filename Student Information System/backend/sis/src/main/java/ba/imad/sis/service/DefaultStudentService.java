package ba.imad.sis.service;

import ba.imad.sis.domain.Student;
import ba.imad.sis.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultStudentService implements StudentService{

    private final StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void createStudent(Student student) {
        boolean exists = studentRepository.existsByIndex(student.getIndex());

        if(!exists) {
            studentRepository.save(student);
        } else {
            throw new IllegalStateException("Student with index " + student.getIndex() + "already exists");
        }
    }

    @Override
    public Student getStudentByIndex(String index) {
        return studentRepository.findByIndex(index).orElse(null);
    }

    @Override
    public void deleteStudentByIndex(String index) {
        boolean exists = studentRepository.existsByIndex(index);
        if(!exists) {
            throw new IllegalStateException("Student with index " + index + " does not exist");
        }
        studentRepository.deleteByIndex(index);
    }

    @Override
    @Transactional
    public void updateStudentByIndex(String index, Student newStudent) {
        Student student = studentRepository.findByIndex(index).orElseThrow(() -> new IllegalStateException("Student with index " + index + " does not exist"));


        if(newStudent.getFirstName() != null &&
                !newStudent.getFirstName().isEmpty() &&
                !Objects.equals(newStudent.getFirstName(), student.getFirstName())) {
            student.setFirstName(newStudent.getFirstName());
        }

        if(newStudent.getLastName() != null &&
                !newStudent.getLastName().isEmpty() &&
                !Objects.equals(newStudent.getLastName(), student.getLastName())) {
            student.setLastName(newStudent.getLastName());
        }

        if(newStudent.getEmail() != null &&
                !newStudent.getEmail().isEmpty()
                && !Objects.equals(newStudent.getEmail(), student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findByEmail(newStudent.getEmail());

            studentOptional.ifPresent(exception -> {throw new IllegalStateException("Student with email " + newStudent.getEmail() + " already exists");});

            student.setEmail(newStudent.getEmail());
        }

    }
}

