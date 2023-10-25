package com.jsp.lic.controller;

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

import com.jsp.lic.dto.AddressDto;
import com.jsp.lic.entity.Address;
import com.jsp.lic.service.AddressService;
import com.jsp.lic.util.ResponceStructure;

import lombok.Delegate;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping
	public ResponseEntity<ResponceStructure<AddressDto>> SaveAddress(@RequestBody Address address) {
		return service.saveAddress(address);
	}

	@PutMapping
	public ResponseEntity<ResponceStructure<AddressDto>> updateAddressById(@RequestParam int addressId,
			@RequestBody Address address) {
		return service.updateAddessById(addressId, address);
	}

	@GetMapping
	public ResponseEntity<ResponceStructure<AddressDto>> findAddressById(@RequestParam int addressId) {
		return service.findAddressById(addressId);
	}

	@DeleteMapping
	public ResponseEntity<ResponceStructure<AddressDto>> deleteAddressById(@RequestParam int addressId) {
		return service.deleteAddressById(addressId);

	}

}
