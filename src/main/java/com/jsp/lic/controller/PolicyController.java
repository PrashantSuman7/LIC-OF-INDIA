package com.jsp.lic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.lic.dto.PolicyDto;
import com.jsp.lic.entity.Policy;
import com.jsp.lic.service.PolicyService;
import com.jsp.lic.util.ResponceStructure;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	@Autowired
	private PolicyService service;
	
	@PostMapping
	public ResponseEntity<ResponceStructure<PolicyDto>> savePolicy(@RequestBody Policy policy)
	{
		return service.savePolicy(policy);
	}
	@GetMapping
	public ResponseEntity<ResponceStructure<Policy>> fetchPolicyById(@RequestParam int policy_Number){
		return service.fetchPolicyById(policy_Number);
	}
	
	@GetMapping("/getallpolices")
	public List<Policy> getAllPolicy() {
		return service.findAll();
	}
}
