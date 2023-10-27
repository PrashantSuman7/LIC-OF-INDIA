package com.jsp.lic.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lic.entity.Address;
import com.jsp.lic.entity.Agent;
import com.jsp.lic.entity.Policy;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PolicyHolderDto {
	private int policyId;
	private String name;
	private String email;
	private long phone;
	private LocalDate dateOfBirth;
	private LocalDate openingDate;
	private LocalDate nextDeuDate=openingDate;
	private double premium;
	private double maturity=1.0;
	
	@OneToOne
	private AddressDto addressDto;
	
	@ManyToOne
	private AgentDto agentdto;
	
	@OneToMany
	private List<Policy> policies;

}
