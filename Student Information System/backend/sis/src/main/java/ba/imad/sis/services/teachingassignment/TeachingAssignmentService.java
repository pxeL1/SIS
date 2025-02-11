package ba.imad.sis.services.teachingassignment;

import ba.imad.sis.domain.TeachingAssignment;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.TeachingAssignmentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeachingAssignmentService {
    Page<TeachingAssignment> getTeachingAssignments(Pageable pageable);
    TeachingAssignment getTeachingAssignmentById(Long id);
    Page<TeachingAssignment> getTeachingAssignmentsByProffessorId(Long proffessorId, Pageable pageable);
    Page<TeachingAssignment> getTeachingAssignmentsByCourseId(Long courseId, Pageable pageable);
    Page<TeachingAssignment> getFilteredTeachingAssignments(List<FilterDTO> filterDTO, Pageable pageable);
    TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignment);
    void deleteTeachingAssignmentById(Long id);
    void deleteAllProfessorTeachingAssignments(Long proffessorId);
    void deleteAllCourseTeachingAssignments(Long courseId);
    void updateTeachingAssignment(TeachingAssignmentUpdateRequest newTeachingAssignment, Long id);
}
