package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Magics;

public interface MagicsRepository extends JpaRepository<Magics, Long> {
}
