package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.NPC;

public interface NPCRepository extends JpaRepository<NPC, Long> {
}
