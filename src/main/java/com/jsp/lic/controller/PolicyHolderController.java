package com.jsp.lic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.lic.dto.PolicyHolderDto;
import com.jsp.lic.entity.Policy;
import com.jsp.lic.entity.PolicyHolder;
import com.jsp.lic.service.PolicyHolderService;
import com.jsp.lic.util.ResponceStructure;

@RestController
@RequestMapping("/policyholder")
public class PolicyHolderController {
	
	@Autowired
	private PolicyHolderService service;
	
	
	@PostMapping
	public ResponseEntity<ResponceStructure<PolicyHolderDto>> savePolicyholder
	(@RequestParam int agentId,@RequestParam int addressId, @RequestParam int[] policy_Number, @RequestBody PolicyHolder policyHolder)
	{
		return service.savePolicyholder(agentId,addressId,policy_Number, policyHolder);
	}
	
	@GetMapping
	public ResponseEntity<ResponceStructure<PolicyHolderDto>> fetchPolicyHolderById(@RequestParam int policyId)
	{
		return service.fetchPolicyHolderById(policyId);
	}
	
	@PutMapping
	public ResponseEntity<ResponceStructure<PolicyHolderDto>> updatePolicyHolderById
	(@RequestParam int policyId, @RequestBody PolicyHolder policyHolder)
	{
		return service.updatePolicyHolderById(policyId , policyHolder);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponceStructure<PolicyHolderDto>> deletePolicyHolderById
	(@RequestParam int policyId)
	{
		return service.deletePolicyHolderById(policyId);
	}
	
	@GetMapping("/getAllPolicy")
	public List<Policy> getAllPolicyById
	(@RequestParam int policyId)
	{
		return service.getAllPolicyById(policyId);
	}
	
	@GetMapping("/loginholder")
	public ResponseEntity<ResponceStructure<PolicyHolderDto>> logInPolicyHolderByEmailId
	(@RequestParam String email, @RequestParam String password) {
		return service.logInPolicyHolderByEmailById(email , password);
	}
	

}
