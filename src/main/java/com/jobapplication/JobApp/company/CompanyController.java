package com.jobapplication.JobApp.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = companyService.getAllCompanies();
		return ResponseEntity.ok(companies);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.getCompany(id);
		if (company != null) {
			return new ResponseEntity<>(company, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<String>("company added successfully", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		companyService.updateCompany(id, company);
		return new ResponseEntity<String>("Company Updated successfully", HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		Boolean deleted = companyService.deleteCompany(id);
		if (deleted) {
			return new ResponseEntity<String>("Company deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
