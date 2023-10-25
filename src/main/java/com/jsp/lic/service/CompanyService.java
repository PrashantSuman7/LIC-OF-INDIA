package com.jsp.lic.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.lic.dao.AddressDao;
import com.jsp.lic.dao.CompanyDao;
import com.jsp.lic.dto.AddressDto;
import com.jsp.lic.dto.CompanyDto;
import com.jsp.lic.entity.Address;
import com.jsp.lic.entity.Company;
import com.jsp.lic.exception.AddressAlreadyMappedtoOther;
import com.jsp.lic.exception.AddressIdNoFonundException;
import com.jsp.lic.exception.CompanyIdNotFoundException;
import com.jsp.lic.util.ResponceStructure;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponceStructure<CompanyDto>> saveCompany(int addressId, Company company) {
		Address dbAddress = addressDao.getAddressById(addressId);
		if (dbAddress != null) {
			if(dbAddress.getAgent()!=null && dbAddress.getPolicyHolder()!=null) {
				throw new AddressAlreadyMappedtoOther("Sorry Address Already Mapped With another Entity");
			}
			dbAddress.setCompany(company);
			company.setAddress(dbAddress);
			
			/*
			 * here we are Updating Database Address table
			 */
			
			Company dbcompany = companyDao.saveCompany(company);
			Address address=dbcompany.getAddress();
			CompanyDto companyDto = this.mapper.map(dbcompany, CompanyDto.class);
		    companyDto.setAddressDto(this.mapper.map(address, AddressDto.class));
			ResponceStructure<CompanyDto> structure = new ResponceStructure<>();
			structure.setMassege("Company Created Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(companyDto);

			return new ResponseEntity<ResponceStructure<CompanyDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new AddressIdNoFonundException("Address Not Found on this Id");
		}

	}

	public ResponseEntity<ResponceStructure<CompanyDto>> findCompanyById(int companyId) {

		Company company = companyDao.getCompanyById(companyId);
		if (company != null) {
			CompanyDto companyDto = this.mapper.map(company, CompanyDto.class);

			ResponceStructure<CompanyDto> structure = new ResponceStructure<>();
			structure.setMassege("Company Founded Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(companyDto);

			return new ResponseEntity<ResponceStructure<CompanyDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new CompanyIdNotFoundException("Sorry we can't find any company on this id");
		}
	}

	public ResponseEntity<ResponceStructure<CompanyDto>> updateCompanyById(int companyId, Company company) {
		Company dbCompany = companyDao.updateCompanyById(companyId, company);
		if (dbCompany != null) {
			CompanyDto companyDto = this.mapper.map(dbCompany, CompanyDto.class);

			ResponceStructure<CompanyDto> structure = new ResponceStructure<>();
			structure.setMassege("company Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(companyDto);
			return new ResponseEntity<ResponceStructure<CompanyDto>>(structure, HttpStatus.OK);
		}else {
			throw new CompanyIdNotFoundException("Sorry we can't find any company on this id");
		}
	}

	public ResponseEntity<ResponceStructure<CompanyDto>> deleteCompanyById(int companyId) {
		Company company=companyDao.deleteCompanyById(companyId);
		if(company!=null)
		{
			CompanyDto companyDto = this.mapper.map(company, CompanyDto.class);

			ResponceStructure<CompanyDto> structure = new ResponceStructure<>();
			structure.setMassege("company Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(companyDto);
			return new ResponseEntity<ResponceStructure<CompanyDto>>(structure, HttpStatus.GONE);
		}else {
			throw new CompanyIdNotFoundException("Sorry we can't find any company on this id");
		}
		
	}

}
