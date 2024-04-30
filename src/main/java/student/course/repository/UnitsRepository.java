package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Units;

public interface UnitsRepository extends JpaRepository<Units, Long> {
}
