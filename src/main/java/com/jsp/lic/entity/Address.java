package com.jsp.lic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String streat_name;
	private String district_name; 
	private String state;
	private String country;
	private int pin_code;
	@OneToOne
	@JoinColumn
	private Company company;
	@OneToOne
	@JoinColumn
	private Agent agent;
	
	@OneToOne
	@JoinColumn
	private PolicyHolder policyHolder;
}
