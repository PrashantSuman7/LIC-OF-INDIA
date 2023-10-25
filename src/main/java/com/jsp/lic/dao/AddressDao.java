package com.jsp.lic.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.lic.entity.Address;
import com.jsp.lic.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address address) {
		return repo.save(address);
	}

	public Address getAddressById(int addressId) {
		Optional<Address> optional = repo.findById(addressId);
		if (optional.isPresent()) {
			 Address address=optional.get();
			 return address;

		}
		return null;
	}

	public Address updateAddressById(int addressId, Address address) {
		Optional<Address> optional = repo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}
		return null;
	}

	public Address deleteAddressById(int addressId) {
		Optional<Address> optional = repo.findById(addressId);
		if (optional.isPresent()) {
			Address dbaddress = optional.get();
			repo.deleteById(addressId);
			return dbaddress;
	}
		return null;
	}
}


