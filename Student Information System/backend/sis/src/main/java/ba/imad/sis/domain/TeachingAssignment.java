package ba.imad.sis.domain;

import jakarta.persistence.*;

@Entity
public class TeachingAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semester;
    private String role;
    @ManyToOne
    private User professor;
    @ManyToOne
    private Course course;

    public TeachingAssignment() {
    }

    public TeachingAssignment(String semester, String role, User professor, Course course) {
        this.semester = semester;
        this.role = role;
        this.professor = professor;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
