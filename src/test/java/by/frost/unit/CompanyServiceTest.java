package by.frost.unit;

import by.frost.database.entity.Company;
import by.frost.database.repository.CompanyRepository;
import by.frost.dto.CompanyDto;
import by.frost.listener.EntityEvent;
import by.frost.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;
    private static final String COMPANY_NAME = "COMPANY_NAME";
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() throws SQLException {
        Mockito.doReturn(Optional.of(Company.builder().build()))
                .when(companyRepository).findById(COMPANY_ID);
        var realResult = companyService.findById(COMPANY_ID);

        assertTrue(realResult.isPresent());

        var result = new CompanyDto(COMPANY_ID, COMPANY_NAME);

        realResult.ifPresent(real -> assertEquals(result, real));

        verify(eventPublisher).publishEvent(any(EntityEvent.class));

    }
}
