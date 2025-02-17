package by.frost.database.repository;


import by.frost.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstnameContainingAndLastnameContaining(String fragmentFirstname, String fragmentLastname);

    Optional<User> findByUsername(String username);
}
