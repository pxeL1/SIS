package ba.imad.sis.services.teachingassignment;

import ba.imad.sis.domain.TeachingAssignment;
import ba.imad.sis.dtos.FilterDTO;
import ba.imad.sis.dtos.TeachingAssignmentUpdateRequest;
import ba.imad.sis.repositories.TeachingAssignmentRepository;
import ba.imad.sis.specifications.ProfessorSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeachingAssignmentService implements TeachingAssignmentService {
    private final TeachingAssignmentRepository teachingAssignmentRepository;

    public DefaultTeachingAssignmentService(TeachingAssignmentRepository teachingAssignmentRepository) {
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }

    @Override
    public Page<TeachingAssignment> getTeachingAssignments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teachingAssignmentRepository.findAll(pageable);
    }

    @Override
    public TeachingAssignment getTeachingAssignmentById(Long id) {
        return teachingAssignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find teaching assignment with id: " + id));
    }

    @Override
    public Page<TeachingAssignment> getTeachingAssignmentsByProffessorId(Long proffessorId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teachingAssignmentRepository.findAllByProfessorId(proffessorId, pageable).orElse(Page.empty(pageable));
    }

    @Override
    public Page<TeachingAssignment> getTeachingAssignmentsByCourseId(Long courseId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teachingAssignmentRepository.findAllByCourseId(courseId, pageable).orElse(Page.empty(pageable));
    }

    @Override
    public Page<TeachingAssignment> getFilteredTeachingAssignments(List<FilterDTO> filterDTO, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teachingAssignmentRepository.findAll(ProfessorSpecification.columnEqualsTeachingAssignment(filterDTO), pageable);
    }

    @Override
    public TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignment) {
        boolean exists = teachingAssignmentRepository.existsByProfessorIdAndCourseId(teachingAssignment.getProfessor().getId(), teachingAssignment.getCourse().getId());

        if(!exists) {
            teachingAssignmentRepository.save(teachingAssignment);
        }
        else {
            throw new RuntimeException("Teaching assignment already exists");
        }

        return teachingAssignment;
    }

    @Override
    public void deleteTeachingAssignmentById(Long id) {
        boolean exists = teachingAssignmentRepository.existsById(id);

        if(!exists) {
            throw new RuntimeException("Teaching Assignment for this course does not exist");
        }

        teachingAssignmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllProfessorTeachingAssignments(Long proffessorId) {
        boolean exists = teachingAssignmentRepository.existsByProfessorId(proffessorId);

        if(!exists) {
            throw new RuntimeException("Teaching Assignment for this professor does not exist");
        }

        teachingAssignmentRepository.deleteAllByProfessorId(proffessorId);
    }

    @Override
    @Transactional
    public void deleteAllCourseTeachingAssignments(Long courseId) {
        boolean exists = teachingAssignmentRepository.existsByCourseId(courseId);

        if(!exists) {
            throw new RuntimeException("Teaching Assignment for this course does not exist");
        }

        teachingAssignmentRepository.deleteAllByCourseId(courseId);
    }

    @Override
    @Transactional
    public void updateTeachingAssignment(TeachingAssignmentUpdateRequest newTeachingAssignment, Long id) {
        TeachingAssignment oldTeachingAssignment = getTeachingAssignmentById(id);

        oldTeachingAssignment.setRole(newTeachingAssignment.role());
        oldTeachingAssignment.setSemester(newTeachingAssignment.semester());

        teachingAssignmentRepository.save(oldTeachingAssignment);
    }
}
