package ba.imad.sis.domain;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private Professor professor;

    public Course() {
    }

    public Course(String name, String description, Professor professor) {
        this.name = name;
        this.description = description;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
