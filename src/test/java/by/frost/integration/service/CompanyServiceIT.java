package by.frost.integration.service;


import by.frost.annotation.IT;
import by.frost.config.DatabaseProperties;
import by.frost.dto.CompanyDto;
import by.frost.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;
    private static final String COMPANY_NAME = "Какая то компания";
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() throws SQLException {

        var realResult = companyService.findById(COMPANY_ID);

        assertTrue(realResult.isPresent());

        var result = new CompanyDto(COMPANY_ID, COMPANY_NAME);

        realResult.ifPresent(real -> assertEquals(result, real));

    }
}
