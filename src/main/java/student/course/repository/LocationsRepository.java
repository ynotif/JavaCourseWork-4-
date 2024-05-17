package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Locations;

public interface LocationsRepository extends JpaRepository<Locations, Long> {
}
