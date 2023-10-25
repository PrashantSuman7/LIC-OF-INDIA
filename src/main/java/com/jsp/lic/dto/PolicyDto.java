package com.jsp.lic.dto;



import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PolicyDto {
	private int policy_Number;
	private String policy_name;
	private String policy_type;
	private double premium;
	private int term;
	private double sum_assured=1;
	
}
