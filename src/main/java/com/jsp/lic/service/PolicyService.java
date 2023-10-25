package com.jsp.lic.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.lic.dao.PolicyDao;
import com.jsp.lic.dto.PolicyDto;
import com.jsp.lic.dto.PolicyHolderDto;
import com.jsp.lic.entity.Policy;
import com.jsp.lic.util.ResponceStructure;

@Service
public class PolicyService {
	@Autowired
	private PolicyDao policyDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponceStructure<PolicyDto>> savePolicy(Policy policy) {
		Policy dbPolicy = policyDao.savePolicy(policy);
		if (dbPolicy != null) {
			dbPolicy.setSum_assured(dbPolicy.getPremium() * dbPolicy.getTerm());
			PolicyDto policyDto = this.mapper.map(dbPolicy, PolicyDto.class);

			ResponceStructure<PolicyDto> responceStructure = new ResponceStructure<>();
			responceStructure.setMassege("Policy Added Successfully");
			responceStructure.setStatus(HttpStatus.CREATED.value());
			responceStructure.setData(policyDto);
			return new ResponseEntity<ResponceStructure<PolicyDto>>(responceStructure, HttpStatus.CREATED);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponceStructure<Policy>> fetchPolicyById(int policy_Number) {
		Policy dbPolicy = policyDao.getPolicyById(policy_Number);
		if (dbPolicy != null) {
			ResponceStructure<Policy> responceStructure = new ResponceStructure<>();
			responceStructure.setMassege("Policy Founded Successfully");
			responceStructure.setStatus(HttpStatus.FOUND.value());
			responceStructure.setData(dbPolicy);
			return new ResponseEntity<ResponceStructure<Policy>>(responceStructure, HttpStatus.FOUND);

		} else {
			return null;
		}
	}

	public List<Policy> findAll() {
		List<Policy> policies = policyDao.getAllPolices();
		List<Policy> list = new ArrayList<>();
		for (Policy policy : policies) {
			if (policy != null) {
				list.add(policy);
			}
		}
		return list;
	}

}
