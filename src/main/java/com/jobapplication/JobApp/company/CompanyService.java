package com.jobapplication.JobApp.company;

import java.util.List;

public interface CompanyService {

	List<Company> getAllCompanies();
	
	void createCompany(Company company);
	
	Company getCompany(Long id);
	
	Boolean updateCompany(Long id, Company company);
	
	Boolean deleteCompany(Long id);

}
