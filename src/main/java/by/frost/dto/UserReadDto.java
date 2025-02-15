package by.frost.dto;

import by.frost.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Integer id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    CompanyDto company;
}
