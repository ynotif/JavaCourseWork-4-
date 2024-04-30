package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Bosses;

public interface BossesRepository extends JpaRepository<Bosses, Long> {
}
