package com.jsp.lic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.lic.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
