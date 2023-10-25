package com.jsp.lic.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.lic.entity.Company;
import com.jsp.lic.repo.CompanyRepo;

@Repository
public class CompanyDao {
	@Autowired
	private CompanyRepo repo;

	public Company saveCompany(Company company) {
		return repo.save(company);
	}

	public Company getCompanyById(int companyId) {
		if (repo.findById(companyId).isPresent()) {
			return repo.findById(companyId).get();
		} else {
			return null;
		}
	}

	public Company updateCompanyById(int companyId, Company company) {
		Optional<Company> optional = repo.findById(companyId);
		if (optional.isPresent()) {
			Company dbCompany = optional.get();
			company.setCompanyId(companyId);
			return repo.save(company);

		}
		return null;
	}

	public Company deleteCompanyById(int companyId) {
		if (repo.findById(companyId).isPresent()) {
			Company dbCompany = repo.findById(companyId).get();
			repo.delete(dbCompany);
			return dbCompany;
		} else {
			return null;
		}
	}

}
