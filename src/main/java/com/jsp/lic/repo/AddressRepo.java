package com.jsp.lic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.lic.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
