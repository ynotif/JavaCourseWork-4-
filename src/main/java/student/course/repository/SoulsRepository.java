package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Souls;

public interface SoulsRepository extends JpaRepository<Souls, Long> {
}
