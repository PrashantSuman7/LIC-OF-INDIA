package com.jsp.lic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonNotBuyAnyPolicyException extends RuntimeException {
	private String message;

}
