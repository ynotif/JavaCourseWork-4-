package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Weapons;

public interface WeaponsRepository extends JpaRepository<Weapons, Long> {
}
