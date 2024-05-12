package student.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

}
