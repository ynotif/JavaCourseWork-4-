package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.UserRoles;

public interface UsersRolesRepository extends JpaRepository<UserRoles, Long> {
}
