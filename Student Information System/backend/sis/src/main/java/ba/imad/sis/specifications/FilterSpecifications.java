package ba.imad.sis.specifications;

import ba.imad.sis.dtos.FilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FilterSpecifications {
    public static <T> Specification<T> columnEquals(List<FilterDTO> filterDTOList) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filterDTOList.forEach(filterDTO -> {
                Predicate predicate = builder.equal(root.get(filterDTO.columnName()),filterDTO.columnValue());
                predicates.add(predicate);
            });
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        );
    }
}

