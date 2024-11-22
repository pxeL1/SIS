package ba.imad.sis.repositories;

import ba.imad.sis.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByIndex(String index);
    void deleteByIndex(String index);
    boolean existsByIndex(String index);
    Optional<Student> findByEmail(String email);
}
