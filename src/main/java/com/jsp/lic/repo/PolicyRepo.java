package com.jsp.lic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.lic.entity.Policy;

public interface PolicyRepo extends JpaRepository<Policy, Integer> {

}
