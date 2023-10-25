package com.jsp.lic.dto;




import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private int addressId;
	private String streat_name;
	private String district_name;
	private String state;
	private String country;
	private int pin_code;
}
