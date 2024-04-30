package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Armors;

public interface ArmorsRepository extends JpaRepository<Armors, Long>{
}
