package com.jsp.lic.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.lic.entity.Agent;

public interface AgentRepo extends JpaRepository<Agent, Integer> {
@Query("select a from Agent a where a.agent_email=?1")
	public Optional<Agent> findAgentByEmail(String agent_email);

}
