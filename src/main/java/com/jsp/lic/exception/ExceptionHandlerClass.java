package com.jsp.lic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.lic.util.ResponceStructure;

@RestControllerAdvice
public class ExceptionHandlerClass {
	
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> addressIdNotFound(AddressIdNoFonundException a)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry AddressId not Present In database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(a.getMassage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> companyIdNotFound(CompanyIdNotFoundException a)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry comapnyId not Present In database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(a.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> agentIdNotFound(AgentIdNotFountException a)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry Agent not Present In database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(a.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> addressAlredyMapped(AddressAlreadyMappedtoOther ex)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry Address Already mapped With other Entity");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> policyHolderNotPresent(PolicyHolderNotPresent ex)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry no Id Found In database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> personNotBuyPolicy(PersonNotBuyAnyPolicyException ex)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Sorry no Police's Fonded In Database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> passwordMissMatch(PasswordMissMatchException ex)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Please Check Your Password");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponceStructure<String>> emailIdNotFound(EmailIdNotFountException ex)
	{
		ResponceStructure<String> structure=new ResponceStructure<>();
		structure.setMassege("Please Write Currect Email Id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}

}
