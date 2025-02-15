package by.frost.service;

import by.frost.database.repository.CompanyRepository;
import by.frost.dto.CompanyDto;
import by.frost.listener.AccessType;
import by.frost.listener.EntityEvent;
import by.frost.mapper.CompanyMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private final CompanyMapper companyMapper;

    public Optional<CompanyDto> findById(Integer id) {

        return companyRepository.findById(id).map(company -> {
            applicationEventPublisher.publishEvent(new EntityEvent(company, AccessType.READ));
            return new CompanyDto(company.getId(), company.getName());
        });
    }

    public List<CompanyDto> findAll()  {

        return companyRepository.findAll().stream()
                .map(companyMapper::toCompanyDto).toList();
    }
}
