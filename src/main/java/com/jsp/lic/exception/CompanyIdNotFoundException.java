package com.jsp.lic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyIdNotFoundException extends RuntimeException {
	
	private String message;

}
