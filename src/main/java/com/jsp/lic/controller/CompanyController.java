package com.jsp.lic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.lic.dto.CompanyDto;
import com.jsp.lic.entity.Company;
import com.jsp.lic.service.CompanyService;
import com.jsp.lic.util.ResponceStructure;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@PostMapping
	public ResponseEntity<ResponceStructure<CompanyDto>> saveCompany(@RequestParam int addressId,
			@RequestBody Company company) {
		return service.saveCompany(addressId, company);
	}

	@GetMapping
	public ResponseEntity<ResponceStructure<CompanyDto>> findCompanyById(@RequestParam int companyId) {
		return service.findCompanyById(companyId);
	}
	
	@PutMapping
	public ResponseEntity<ResponceStructure<CompanyDto>> updateCompanyById(@RequestParam int companyId, @RequestBody Company company)
	{
		return service.updateCompanyById(companyId, company);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponceStructure<CompanyDto>> deleteCompanyById(@RequestParam int companyId)
	{
		return service.deleteCompanyById(companyId);
	}

}
