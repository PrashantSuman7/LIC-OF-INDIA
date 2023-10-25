package com.jsp.lic.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.lic.entity.PolicyHolder;

public interface PolicyHolderRepo extends JpaRepository<PolicyHolder, Integer> {
@Query("select h from PolicyHolder h where h.email=?1")
	public Optional<PolicyHolder> findPolicyHolderByEmailId(String email);

}
