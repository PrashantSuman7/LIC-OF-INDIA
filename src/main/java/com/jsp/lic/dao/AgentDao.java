package com.jsp.lic.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.lic.entity.Agent;
import com.jsp.lic.repo.AgentRepo;

@Repository
public class AgentDao {

	@Autowired
	private AgentRepo repo;

	public Agent saveAgent(Agent agent) {
		return repo.save(agent);
	}

	public Agent updateAgent(int agentId, Agent agent) {
		Optional<Agent> optional = repo.findById(agentId);
		if (optional.isPresent()) {
			Agent dbAgent = optional.get();
			agent.setAgentId(agentId);
			agent.setAddress(dbAgent.getAddress());
			agent.setCompany(dbAgent.getCompany());

			return repo.save(agent);
		} else {
			return null;
		}
	}

	public Agent findAgentById(int agentId) {
		Optional<Agent> optional = repo.findById(agentId);
		if (optional.isPresent()) {
			Agent dbAgent = optional.get();
			return dbAgent;
		} else {
			return null;
		}
	}

	public Agent deleteAgentById(int agentId) {
		Optional<Agent> optional = repo.findById(agentId);
		if (optional.isPresent()) {
			Agent dbAgent = optional.get();
			repo.deleteById(agentId);
			return dbAgent;
		} else {
			return null;
		}
	}

	public List<Agent> getAllAgent() {
		return repo.findAll();
	}

	public Agent findAgentByEmailId(String agent_email) {
		Optional<Agent> optional = repo.findAgentByEmail(agent_email);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
