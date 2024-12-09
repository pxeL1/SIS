package ba.imad.sis.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue
    private Long id;
    private Integer points;
    @Nullable
    private Integer grade;
    @OneToOne
    private Course course;
    @OneToOne
    private Student student;
}
