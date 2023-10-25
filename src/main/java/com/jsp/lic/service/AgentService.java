package com.jsp.lic.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.ErrorListener;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.lic.dao.AddressDao;
import com.jsp.lic.dao.AgentDao;
import com.jsp.lic.dao.CompanyDao;
import com.jsp.lic.dto.AddressDto;
import com.jsp.lic.dto.AgentDto;
import com.jsp.lic.dto.CompanyDto;
import com.jsp.lic.entity.Address;
import com.jsp.lic.entity.Agent;
import com.jsp.lic.entity.Company;
import com.jsp.lic.exception.AddressAlreadyMappedtoOther;
import com.jsp.lic.exception.AddressIdNoFonundException;
import com.jsp.lic.exception.AgentIdNotFountException;
import com.jsp.lic.exception.CompanyIdNotFoundException;
import com.jsp.lic.exception.EmailIdNotFountException;
import com.jsp.lic.exception.PasswordMissMatchException;
import com.jsp.lic.util.ResponceStructure;

@Service
public class AgentService {
	/*
	 * AutoWried use for getting Dependency Injection Facility....
	 */
	@Autowired
	private AgentDao agentDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private AddressDao addressDao;
	/*
	 * Model-mapper Is Used for Converting Entity to Dto Class.
	 */

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponceStructure<AgentDto>> saveAgent(int companyId, int addressId, Agent agent) {
		Address dbAddress = addressDao.getAddressById(addressId);

		if (dbAddress != null) {
			Company dbCompany = companyDao.getCompanyById(companyId);

			/*
			 * Here We are Checking the Address Entity mapped with Company and PolicyHolder.
			 * if address Mapped with any Entity then we throwing One Exception Like Address
			 * Already mapped.....
			 */

			/*
			 * Here We Are Updating Address Table Because Address table is having agent id
			 * as Foreign key
			 */

			if (dbCompany != null) {
				if (dbAddress.getCompany() != null && dbAddress.getPolicyHolder() != null) {
					throw new AddressAlreadyMappedtoOther("Sorry Address Id Already mapped to Another Entity");
				}
				dbAddress.setAgent(agent);
				agent.setAddress(dbAddress);
				agent.setCompany(dbCompany);
				Agent dbAgent = agentDao.saveAgent(agent);
				Company company = dbAgent.getCompany();
				Address address = dbAgent.getAddress();
				AgentDto agentDto = this.mapper.map(dbAgent, AgentDto.class);
				agentDto.setAddressDto(this.mapper.map(address, AddressDto.class));
				agentDto.setCompanyDto(this.mapper.map(company, CompanyDto.class));
				ResponceStructure<AgentDto> structure = new ResponceStructure<>();
				structure.setMassege("Agent Save Successfully......");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(agentDto);
				return new ResponseEntity<ResponceStructure<AgentDto>>(structure, HttpStatus.CREATED);

			} else {
				throw new CompanyIdNotFoundException("Sorry we can't find any Company For this iD     ");
			}

		} else {
			throw new AddressIdNoFonundException("Please Enter valid AddressId");
		}

	}

	public ResponseEntity<ResponceStructure<AgentDto>> updateAgent(int agentId, Agent agent) {

		Agent dbAgent = agentDao.updateAgent(agentId, agent);
		if (dbAgent != null) {
			AgentDto agentDto = this.mapper.map(dbAgent, AgentDto.class);
			ResponceStructure<AgentDto> structure = new ResponceStructure<>();
			structure.setMassege("Agent Updated Successfully......");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(agentDto);
			return new ResponseEntity<ResponceStructure<AgentDto>>(structure, HttpStatus.OK);

		} else {
			throw new AgentIdNotFountException("Sorry We Can't find Any Agent for this Id");
		}
	}

	public ResponseEntity<ResponceStructure<AgentDto>> getAgentById(int agentId) {
		Agent dbAgent = agentDao.findAgentById(agentId);
		if (dbAgent != null) {
			AgentDto agentDto = this.mapper.map(dbAgent, AgentDto.class);
			ResponceStructure<AgentDto> structure = new ResponceStructure<>();
			structure.setMassege("Agent Founded Successfully......");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(agentDto);
			return new ResponseEntity<ResponceStructure<AgentDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new AgentIdNotFountException("No Agent Present in Db for this Id");
		}
	}

	public ResponseEntity<ResponceStructure<AgentDto>> deleteAgentById(int agentId) {
		Agent dbAgent = agentDao.deleteAgentById(agentId);
		if (dbAgent != null) {
			AgentDto agentDto = this.mapper.map(dbAgent, AgentDto.class);
			ResponceStructure<AgentDto> structure = new ResponceStructure<>();
			structure.setMassege("Agent Deleted Successfully......");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(agentDto);
			return new ResponseEntity<ResponceStructure<AgentDto>>(structure, HttpStatus.GONE);
		} else {
			throw new AgentIdNotFountException("No Agent Present in Db for this Id");
		}
	}

	public List<Agent> getAll() {
		List<Agent> list = new ArrayList<>();
		List<Agent> agents = agentDao.getAllAgent();
		for (Agent agent : agents) {
			list.add(agent);
		}
		return list;
	}

	public ResponseEntity<ResponceStructure<AgentDto>> agentLogInByEmail(String agent_email, String password) {
		Agent dbAgent = agentDao.findAgentByEmailId(agent_email);
		if (dbAgent != null) {
			if (dbAgent.getPassword().equals(password)) {

				AgentDto agentDto = this.mapper.map(dbAgent, AgentDto.class);
				ResponceStructure<AgentDto> structure = new ResponceStructure<>();
				structure.setMassege("LogIn Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(agentDto);
				return new ResponseEntity<ResponceStructure<AgentDto>>(structure, HttpStatus.CREATED);

			} else {
				throw new PasswordMissMatchException("Sorry ! Worng Password");

			}

		} else {
			throw new EmailIdNotFountException("Sorry ! Agent Not Present on this Email...");
		}
	}
}
