package com.jsp.lic.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.lic.entity.Policy;
import com.jsp.lic.repo.PolicyRepo;

@Repository
public class PolicyDao {
	
	@Autowired
	private PolicyRepo repo;

	public Policy getPolicyById(int policyId) {
		if(repo.findById(policyId).isPresent()) {
			return repo.findById(policyId).get();
		}else {
			return null;
		}
	}

	public Policy savePolicy(Policy policy) {
		return repo.save(policy);
	}

	public List<Policy> getAllPolices() {
		return repo.findAll();
	}
	
}
