package com.jsp.lic.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponceStructure<T> {
	private String massege;
	private int status;
	private Object data;

}
