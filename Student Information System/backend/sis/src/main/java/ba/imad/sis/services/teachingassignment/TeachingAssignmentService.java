package ba.imad.sis.services.teachingassignment;

import ba.imad.sis.domain.TeachingAssignment;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.TeachingAssignmentUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeachingAssignmentService {
    Page<TeachingAssignment> getTeachingAssignments(int pageNo, int pageSize);
    TeachingAssignment getTeachingAssignmentById(Long id);
    Page<TeachingAssignment> getTeachingAssignmentsByProffessorId(Long proffessorId, int pageNo, int pageSize);
    Page<TeachingAssignment> getTeachingAssignmentsByCourseId(Long courseId, int pageNo, int pageSize);
    Page<TeachingAssignment> getFilteredTeachingAssignments(List<FilterDTO> filterDTO, int pageNo, int pageSize);
    TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignment);
    void deleteTeachingAssignmentById(Long id);
    void deleteAllProfessorTeachingAssignments(Long proffessorId);
    void deleteAllCourseTeachingAssignments(Long courseId);
    void updateTeachingAssignment(TeachingAssignmentUpdateRequest newTeachingAssignment, Long id);
}
