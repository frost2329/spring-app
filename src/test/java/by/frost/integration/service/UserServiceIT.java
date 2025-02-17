package by.frost.integration.service;

import by.frost.annotation.IT;
import by.frost.database.entity.Role;
import by.frost.dto.UserCreateEditDto;
import by.frost.dto.UserReadDto;
import by.frost.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {
    private static final Integer USER_ID = 3;
    private final UserService userService;


    @Test
    void findAll() {

        List<UserReadDto> result = userService.findAll();
        assertEquals(3, result.size());
        assertThat(result).hasSize(3);
    }

    @Test
    void findById() {
        var result = userService.findById(USER_ID);
        assertTrue(result.isPresent());

    }
    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "dendenden",
                LocalDate.now(),
                "Den",
                "Denov",
                Role.ADMIN,
                1);


        var result = userService.create(userCreateEditDto);

        assertEquals(userCreateEditDto.getUsername(), result.getUsername());
        assertEquals(userCreateEditDto.getBirthDate(), result.getBirthDate());
        assertEquals(userCreateEditDto.getFirstname(), result.getFirstname());
        assertEquals(userCreateEditDto.getLastname(), result.getLastname());
        assertEquals(userCreateEditDto.getRole().name(), result.getRole().name());
        assertEquals(userCreateEditDto.getCompanyId(), result.getCompany().getId());
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "dendenden",
                LocalDate.now(),
                "Den",
                "Denov",
                Role.ADMIN,
                1);


        var MbResult = userService.update(1, userCreateEditDto);
        if(MbResult.isPresent()) {
            UserReadDto result = MbResult.get();
            assertEquals(userCreateEditDto.getUsername(), result.getUsername());
            assertEquals(userCreateEditDto.getBirthDate(), result.getBirthDate());
            assertEquals(userCreateEditDto.getFirstname(), result.getFirstname());
            assertEquals(userCreateEditDto.getLastname(), result.getLastname());
            assertEquals(userCreateEditDto.getRole().name(), result.getRole().name());
            assertEquals(userCreateEditDto.getCompanyId(), result.getCompany().getId());
        }
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-12));
        assertTrue(userService.delete(1));
    }
}
