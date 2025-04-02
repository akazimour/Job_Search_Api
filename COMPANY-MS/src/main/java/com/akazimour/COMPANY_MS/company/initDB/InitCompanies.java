package com.akazimour.COMPANY_MS.company.initDB;

import com.akazimour.COMPANY_MS.company.model.Company;
import com.akazimour.COMPANY_MS.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitCompanies {

    private final CompanyRepository companyRepository;

    public InitCompanies(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void createCompanies() {
        List<Company> companyList = List.of(
                new Company(0, "Harman", "Description of Harman"),
                new Company(0, "Grundfos", "Description of Grundfos"),
                new Company(0, "Denso", "Description of Denso"),
                new Company(0, "Hydro", "Description of Hydro"),
                new Company(0, "Arconic", "Description of Arconic")
        );
        companyRepository.saveAll(companyList);
    }
}
