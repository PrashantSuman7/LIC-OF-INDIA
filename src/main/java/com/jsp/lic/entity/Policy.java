package com.jsp.lic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policy_Number;
	private String policy_name;
	private String policy_type;
	private double premium;
	private int term;
	private double sum_assured=1;
	@ManyToOne
	@JoinColumn
	private PolicyHolder policyHolder ;

}
