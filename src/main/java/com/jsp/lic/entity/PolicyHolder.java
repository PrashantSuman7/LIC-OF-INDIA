package com.jsp.lic.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class PolicyHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyId;
	private String name;
	private String email;
	private String password;
	private long phone;
	private LocalDate dateOfBirth;
	private LocalDate openingDate;
	private LocalDate nextDeuDate=openingDate;
	private double premium;
	private double maturity=1.0;
	@OneToOne(mappedBy = "policyHolder")
	private Address address;
	@ManyToOne
	@JoinColumn
	private Agent agent;
	
	@OneToMany(mappedBy = "policyHolder")
	private List<Policy> policies;
	
}
