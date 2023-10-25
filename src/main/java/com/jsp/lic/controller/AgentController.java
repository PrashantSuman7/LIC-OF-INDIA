package com.jsp.lic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.lic.dto.AgentDto;
import com.jsp.lic.entity.Agent;
import com.jsp.lic.service.AgentService;
import com.jsp.lic.util.ResponceStructure;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	private AgentService service;
	
	@PostMapping
	public ResponseEntity<ResponceStructure<AgentDto>> saveAgent(@RequestParam int companyId, @RequestParam int addressId, @RequestBody Agent agent)
	{
		return service.saveAgent(companyId, addressId , agent);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponceStructure<AgentDto>> agentLogInByEmail(@RequestParam String agent_email ,@RequestParam String password) {
		return service.agentLogInByEmail(agent_email, password);
	}
	
	@PutMapping
	public ResponseEntity<ResponceStructure<AgentDto>> updateAgent(@RequestParam int agentId, @RequestBody Agent agent)
	{
		return service.updateAgent(agentId, agent);
	}
	
	@GetMapping
	public ResponseEntity<ResponceStructure<AgentDto>> getAgentById(@RequestParam int agentId)
	{
		return service.getAgentById(agentId);
	}
	@DeleteMapping
	public ResponseEntity<ResponceStructure<AgentDto>> deleteAgentById(@RequestParam int agentId)
	{
		return service.deleteAgentById(agentId);
	}
	
	@GetMapping("/getAll")
	public List<Agent> getAllAgent()
	{
		return service.getAll();
	}
	
	

}
