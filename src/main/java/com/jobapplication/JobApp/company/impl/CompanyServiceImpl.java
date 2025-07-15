package com.jobapplication.JobApp.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobapplication.JobApp.company.Company;
import com.jobapplication.JobApp.company.CompanyRepository;
import com.jobapplication.JobApp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();

	}

	@Override
	public void createCompany(Company company) {
		// TODO Auto-generated method stub
		Company comp = new Company();
		comp.setDescription(company.getDescription());
		comp.setName(company.getName());
		comp.setJobs(company.getJobs());
		comp.setReviews(company.getReviews());
		companyRepository.save(comp);
	}

	@Override
	public Company getCompany(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean updateCompany(Long id, Company company) {
		// TODO Auto-generated method stub
		Optional<Company> companyOptional = companyRepository.findById(id);

		if (companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setName(company.getName());
			companyToUpdate.setDescription(company.getDescription());
			companyToUpdate.setJobs(company.getJobs());

			companyRepository.save(companyToUpdate);
			return true;
		}
		return false;

	}

	@Override
	public Boolean deleteCompany(Long id) {
		// TODO Auto-generated method stub

		try {
			companyRepository.deleteById(id);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

}
