package com.jsp.lic.dto;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
	private int companyId;
	private String company_name;
	private String company_email;
	private long contactNumber;
	
	@OneToOne
	private AddressDto addressDto;
}
