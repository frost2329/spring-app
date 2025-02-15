package by.frost.integration.repository;

import by.frost.annotation.IT;
import by.frost.database.entity.Company;
import by.frost.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
@Commit
public class CompanyRepositoryIT {
    @Autowired
    EntityManager entityManager;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void getById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("VK")
                .locales(Map.of(
                        "ru", "Описание",
                        "en", "Description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }

    @Test
    void checkCompanyByFragmentName() {
        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
        assertThat(companies).hasSize(2);
    }
}
