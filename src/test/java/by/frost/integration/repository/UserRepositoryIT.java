package by.frost.integration.repository;

import by.frost.annotation.IT;
import by.frost.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
@Commit
public class UserRepositoryIT  {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    void findAllByFirstnameContainingAndLastnameContaining() {
        var users = userRepository.findAllByFirstnameContainingAndLastnameContaining("a", "a");
        assertThat(users).hasSize(1);
    }
}
