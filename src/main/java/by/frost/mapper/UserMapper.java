package by.frost.mapper;


import by.frost.database.entity.Company;
import by.frost.database.entity.User;
import by.frost.database.repository.CompanyRepository;
import by.frost.dto.CompanyDto;
import by.frost.dto.UserCreateEditDto;
import by.frost.dto.UserReadDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class UserMapper  {
    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final CompanyMapper companyMapper;

    public UserReadDto mapToUserReadDto(User user) {
        var userReadDto = new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getBirthDate(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole(),
                companyMapper.toCompanyDto(user.getCompany()));
        return userReadDto;
    }

    public User mapToUser(UserCreateEditDto userDto) {
        var user = User.builder()
                .username(userDto.getUsername())
                .birthDate(userDto.getBirthDate())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .role(userDto.getRole())
                .company(getCompany(userDto.getCompanyId()))
                .build();
        return user;
    }

    private Company getCompany(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public User mapToUser(UserCreateEditDto userDto, User user) {
        user.setUsername(userDto.getUsername() != null ? userDto.getUsername() : user.getUsername());
        user.setBirthDate(userDto.getBirthDate() != null ? userDto.getBirthDate() : user.getBirthDate());
        user.setFirstname(userDto.getFirstname() != null ? userDto.getFirstname() : user.getFirstname());
        user.setLastname(userDto.getLastname() != null ? userDto.getLastname() : user.getLastname());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : user.getRole());
        if(userDto.getCompanyId()!= null) {
            user.setCompany(getCompany(userDto.getCompanyId()));
        }
        return user;
    }
}
