package by.frost.database.repository;

import by.frost.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
