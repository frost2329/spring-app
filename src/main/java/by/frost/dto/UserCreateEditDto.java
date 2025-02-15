package by.frost.dto;

import by.frost.database.entity.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@ToString
public class UserCreateEditDto {
    @NotNull
    @Size(min = 3, max = 30)
    String username;
    LocalDate birthDate;
    @NotNull
    @Size(min = 2, max = 30)
    String firstname;
    @NotNull
    String lastname;
    Role role;
    Integer companyId;
}
