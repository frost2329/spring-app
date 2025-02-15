package by.frost.mapper;

import by.frost.database.entity.Company;
import by.frost.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyDto toCompanyDto(Company company) {
        return new CompanyDto(company.getId(), company.getName());
    }
}
