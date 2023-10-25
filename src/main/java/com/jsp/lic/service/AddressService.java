package com.jsp.lic.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.lic.dao.AddressDao;
import com.jsp.lic.dto.AddressDto;
import com.jsp.lic.dto.AgentDto;
import com.jsp.lic.dto.CompanyDto;
import com.jsp.lic.dto.PolicyHolderDto;
import com.jsp.lic.entity.Address;
import com.jsp.lic.exception.AddressIdNoFonundException;
import com.jsp.lic.util.ResponceStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponceStructure<AddressDto>> saveAddress(Address address) {
		Address dbAddress=addressDao.saveAddress(address);
		AddressDto addressDto=new AddressDto();
		addressDto.setAddressId(dbAddress.getAddressId());
		addressDto.setStreat_name(dbAddress.getStreat_name());
		addressDto.setDistrict_name(dbAddress.getDistrict_name());
		addressDto.setState(dbAddress.getState());
		addressDto.setCountry(dbAddress.getCountry());
		addressDto.setPin_code(dbAddress.getPin_code());
		//addressDto.setAgentDto(this.mapper.map(dbAddress.getAgent(), AgentDto.class));
		//addressDto.setCompanyDto(this.mapper.map(dbAddress.getCompany(), CompanyDto.class));
		//addressDto.setHolderDto(this.mapper.map(dbAddress.getPolicyHolder(), PolicyHolderDto.class));
		ResponceStructure<AddressDto> structure=new ResponceStructure<>();
		structure.setMassege("Address Added Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(addressDto);
		return new ResponseEntity<ResponceStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<AddressDto>> updateAddessById(int addressId, Address address) {
		Address dbAddress=addressDao.updateAddressById(addressId , address);
		
		AddressDto addressDto=new AddressDto();
		addressDto.setAddressId(dbAddress.getAddressId());
		addressDto.setStreat_name(dbAddress.getStreat_name());
		addressDto.setState(dbAddress.getState());
		addressDto.setCountry(dbAddress.getCountry());
		addressDto.setPin_code(dbAddress.getPin_code());
		
		ResponceStructure<AddressDto> structure=new ResponceStructure<>();
		structure.setMassege("Address Update Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(addressDto);
		return new ResponseEntity<ResponceStructure<AddressDto>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponceStructure<AddressDto>> findAddressById(int addressId) {
		Address dbAddress=addressDao.getAddressById(addressId);
		if(dbAddress!=null) {
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(dbAddress.getAddressId());
			addressDto.setStreat_name(dbAddress.getStreat_name());
			addressDto.setState(dbAddress.getState());
			addressDto.setCountry(dbAddress.getCountry());
			addressDto.setPin_code(dbAddress.getPin_code());
			
			ResponceStructure<AddressDto> structure=new ResponceStructure<>();
			structure.setMassege("Address Founded Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponceStructure<AddressDto>>(structure,HttpStatus.OK);
		}
		else {
			throw new AddressIdNoFonundException("Sorry Id Not Present In Database");
		}
	}

	public ResponseEntity<ResponceStructure<AddressDto>> deleteAddressById(int addressId) {
		Address dbAddress=addressDao.deleteAddressById(addressId);
		if(dbAddress!=null) {
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(dbAddress.getAddressId());
			addressDto.setStreat_name(dbAddress.getStreat_name());
			addressDto.setState(dbAddress.getState());
			addressDto.setCountry(dbAddress.getCountry());
			addressDto.setPin_code(dbAddress.getPin_code());
			
			ResponceStructure<AddressDto> structure=new ResponceStructure<>();
			structure.setMassege("Address Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponceStructure<AddressDto>>(structure,HttpStatus.GONE);
		}
		else {
			throw new AddressIdNoFonundException("Id not Present in DB");
		}
	}
	

}
