package com.jsp.lic.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agentId;
	private String agent_name;
	private String agent_email;
	private String password;
	private long phone;
	private String clubofmember;
	
	
	@OneToOne(mappedBy = "agent")
	private Address address;

	@ManyToOne
	private Company company;
	
	@OneToMany(mappedBy = "agent")
	private List<PolicyHolder> holders;
}
