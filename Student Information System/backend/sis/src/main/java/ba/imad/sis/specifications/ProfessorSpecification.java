package ba.imad.sis.specifications;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.domain.TeachingAssignment;
import ba.imad.sis.dtos.FilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProfessorSpecification {
    public static Specification<ProfessorInformation> columnEqualsProfessor(List<FilterDTO> filtersDTOlist) {
        return ((root, query, builder) -> {
          List<Predicate> predicates = new ArrayList<>();
          filtersDTOlist.forEach(filterDTO -> {
              Predicate predicate = builder.equal(root.get(filterDTO.columnName()), filterDTO.columnValue());
              predicates.add(predicate);
          });
          return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        );
    }

    public static Specification<TeachingAssignment> columnEqualsTeachingAssignment(List<FilterDTO> filtersDTOlist) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filtersDTOlist.forEach(filterDTO -> {
                Predicate predicate = builder.equal(root.get(filterDTO.columnName()), filterDTO.columnValue());
                predicates.add(predicate);
            });
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        );
    }
}
