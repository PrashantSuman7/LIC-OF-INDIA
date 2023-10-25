package com.jsp.lic.dto;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lic.entity.Address;
import com.jsp.lic.entity.Company;
import com.jsp.lic.entity.PolicyHolder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentDto {
	private int agentId;
	private String agent_name;
	private String agent_email;
	private long phone;
	private String clubofmember;
	@OneToOne
	private AddressDto addressDto;
	
	@ManyToOne
	@JsonIgnore
	private CompanyDto companyDto;
	
	@OneToMany(mappedBy = "agent")
	@JsonIgnore
	private List<PolicyHolderDto> holderDtos;
}
