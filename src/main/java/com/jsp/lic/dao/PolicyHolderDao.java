package com.jsp.lic.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.lic.entity.Policy;
import com.jsp.lic.entity.PolicyHolder;
import com.jsp.lic.repo.PolicyHolderRepo;

@Repository
public class PolicyHolderDao {
	@Autowired
	private PolicyHolderRepo repo;

	public PolicyHolder savePolicyHolder(PolicyHolder policyHolder) {
		return repo.save(policyHolder);
	}

	public PolicyHolder findPolicyHolderById(int policyId) {
		if (repo.findById(policyId).isPresent()) {
			return repo.findById(policyId).get();
		} else {
			return null;
		}
	}

	public PolicyHolder updatePolicyHolderById(int policyId, PolicyHolder policyHolder) {
		Optional<PolicyHolder> optional = repo.findById(policyId);
		if (optional.isPresent()) {
			PolicyHolder dbPolicyHolder = optional.get();
			policyHolder.setPolicyId(policyId);
			policyHolder.setAddress(dbPolicyHolder.getAddress());
			policyHolder.setAgent(dbPolicyHolder.getAgent());
			policyHolder.setPolicies(dbPolicyHolder.getPolicies());

			return repo.save(policyHolder);

		} else {
			return null;
		}
	}

	public PolicyHolder deletePolicyHolderById(int policyId) {
		if (repo.findById(policyId).isPresent()) {
			PolicyHolder dbHolder = repo.findById(policyId).get();
			repo.delete(dbHolder);
			return dbHolder;
		} else {
			return null;
		}
	}

	public PolicyHolder findPolicyHolderByEmailId(String email) {
		Optional<PolicyHolder> optional = repo.findPolicyHolderByEmailId(email);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
