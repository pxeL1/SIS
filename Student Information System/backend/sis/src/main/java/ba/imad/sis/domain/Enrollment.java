package ba.imad.sis.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue
    private Long id;
    private Integer points = 0;
    @Nullable
    private Integer grade;
    @OneToOne
    private Course course;
    @OneToOne
    private User student;

    public Enrollment(Integer points, @Nullable Integer grade, Course course, User student) {
        this.points = points;
        this.grade = grade;
        this.course = course;
        this.student = student;
    }

    public Enrollment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Nullable
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(@Nullable Integer grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
