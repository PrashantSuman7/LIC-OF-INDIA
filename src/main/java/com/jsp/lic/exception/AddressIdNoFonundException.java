package com.jsp.lic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressIdNoFonundException extends RuntimeException {
	
	private String massage;

}
