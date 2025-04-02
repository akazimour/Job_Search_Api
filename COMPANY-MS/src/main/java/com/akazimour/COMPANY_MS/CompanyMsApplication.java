package com.akazimour.COMPANY_MS;

import com.akazimour.COMPANY_MS.company.initDB.InitCompanies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CompanyMsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CompanyMsApplication.class, args);
	}
	@Autowired
	InitCompanies initCompanies;
	@Override
	public void run(String... args) throws Exception {
		//initCompanies.createCompanies();
	}
}
