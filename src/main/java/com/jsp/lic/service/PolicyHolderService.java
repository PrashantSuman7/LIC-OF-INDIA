package com.jsp.lic.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.lic.dao.AddressDao;
import com.jsp.lic.dao.AgentDao;
import com.jsp.lic.dao.PolicyDao;
import com.jsp.lic.dao.PolicyHolderDao;
import com.jsp.lic.dto.AddressDto;
import com.jsp.lic.dto.AgentDto;
import com.jsp.lic.dto.PolicyDto;
import com.jsp.lic.dto.PolicyHolderDto;
import com.jsp.lic.entity.Address;
import com.jsp.lic.entity.Agent;
import com.jsp.lic.entity.Policy;
import com.jsp.lic.entity.PolicyHolder;
import com.jsp.lic.exception.AddressAlreadyMappedtoOther;
import com.jsp.lic.exception.AddressIdNoFonundException;
import com.jsp.lic.exception.AgentIdNotFountException;
import com.jsp.lic.exception.EmailIdNotFountException;
import com.jsp.lic.exception.PasswordMissMatchException;
import com.jsp.lic.exception.PersonNotBuyAnyPolicyException;
import com.jsp.lic.exception.PolicyHolderNotPresent;
import com.jsp.lic.util.ResponceStructure;

@Service
public class PolicyHolderService {

	@Autowired
	private PolicyHolderDao holderdao;
	@Autowired
	private AgentDao agentdao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private PolicyDao policyDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponceStructure<PolicyHolderDto>> savePolicyholder(int agentId, int addressId,
			int[] policy_Number, PolicyHolder policyHolder) {
		Address dbAddress = addressDao.getAddressById(addressId);
		if (dbAddress != null) {
			Agent dbAgent = agentdao.findAgentById(agentId);
			if (dbAgent != null) {
				if (dbAddress.getCompany() != null && dbAddress.getAgent() != null) {
					throw new AddressAlreadyMappedtoOther("Sorry AddressId Already mapped with Other Entity");
				}
				policyHolder.setAddress(dbAddress);
				dbAddress.setPolicyHolder(policyHolder);
				policyHolder.setAgent(dbAgent);
				policyHolder.setNextDeuDate(policyHolder.getOpeningDate());

				List<Policy> policies = new ArrayList<>();
				for (int policyNum : policy_Number) {

					/*
					 * Here we declared Two block level variable sumassured10 and sumassured20 and
					 * checking the condition if policy term equals 10 years then multiply same as
					 * 20years term also and updating the Database......
					 */

					Policy dbPolicy = policyDao.getPolicyById(policyNum);

					/*
					 * Here we are Updating Premium next due by using if else statement
					 */
					// nextDeuDate
					String type1 = "Yearly", type2 = "monthly", type3 = "qurterly", type4 = "halfyearly";
					if (dbPolicy.getPolicy_type().equalsIgnoreCase(type1)) {
						policyHolder.setNextDeuDate(policyHolder.getOpeningDate().plusYears(1));
						break;

					} else if (dbPolicy.getPolicy_type().equalsIgnoreCase(type2)) {
						policyHolder.setNextDeuDate(policyHolder.getNextDeuDate().plusMonths(1));
						break;

					} else if (dbPolicy.getPolicy_type().equalsIgnoreCase(type3)) {
						policyHolder.setNextDeuDate(policyHolder.getNextDeuDate().plusMonths(9));
						break;

					} else if (dbPolicy.getPolicy_type().equalsIgnoreCase(type4)) {
						policyHolder.setNextDeuDate(policyHolder.getNextDeuDate().plusMonths(6));
						break;
					}

					policyHolder.setMaturity(policyHolder.getPremium() * dbPolicy.getTerm());
					policies.add(dbPolicy);
					dbPolicy.setPolicyHolder(policyHolder);
				}

				policyHolder.setPolicies(policies);
				

				PolicyHolder dbHolder = holderdao.savePolicyHolder(policyHolder);
				PolicyHolderDto holderDto = this.mapper.map(dbHolder, PolicyHolderDto.class);
				holderDto.setAddressDto(this.mapper.map(dbHolder.getAddress(), AddressDto.class));
				holderDto.setAgentdto(this.mapper.map(dbHolder.getAgent(), AgentDto.class));
				ResponceStructure<PolicyHolderDto> structure = new ResponceStructure<>();
				structure.setMassege("Policy Holder Added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(holderDto);
				return new ResponseEntity<ResponceStructure<PolicyHolderDto>>(structure, HttpStatus.CREATED);

			} else {
				throw new AgentIdNotFountException("Agent Id Not Present in Database");
			}

		} else {
			throw new AddressIdNoFonundException("Sorry Address Id Not Present In Db");
		}

	}

	public ResponseEntity<ResponceStructure<PolicyHolderDto>> fetchPolicyHolderById(int policyId) {
		PolicyHolder dbHolder = holderdao.findPolicyHolderById(policyId);
		if (dbHolder != null) {
			PolicyHolderDto holderDto = this.mapper.map(dbHolder, PolicyHolderDto.class);
			ResponceStructure<PolicyHolderDto> structure = new ResponceStructure<>();
			structure.setMassege("Policy Holder Founded successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(holderDto);
			return new ResponseEntity<ResponceStructure<PolicyHolderDto>>(structure, HttpStatus.FOUND);

		} else {
			throw new PolicyHolderNotPresent("Sorry for this id No PolicyHolder present in Db");
		}
	}

	public ResponseEntity<ResponceStructure<PolicyHolderDto>> updatePolicyHolderById(int policyId,
			PolicyHolder policyHolder) {
		PolicyHolder dbPolicyHolder = holderdao.updatePolicyHolderById(policyId, policyHolder);

		if (dbPolicyHolder != null) {
			PolicyHolderDto dbHolder = this.mapper.map(dbPolicyHolder, PolicyHolderDto.class);
			// PolicyHolderDto holderDto = this.mapper.map(dbPolicyHolder,
			// PolicyHolderDto.class);
			ResponceStructure<PolicyHolderDto> structure = new ResponceStructure<>();
			structure.setMassege("Policy Holder Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbHolder);
			return new ResponseEntity<ResponceStructure<PolicyHolderDto>>(structure, HttpStatus.OK);

		} else {
			throw new PolicyHolderNotPresent("Please ! Check Your PolicyHolder Id ");
		}
	}

	public ResponseEntity<ResponceStructure<PolicyHolderDto>> deletePolicyHolderById(int policyId) {
		PolicyHolder dbHolder = holderdao.findPolicyHolderById(policyId);
		if (dbHolder != null) {
			PolicyHolder dbPolicyHolder = holderdao.deletePolicyHolderById(policyId);
			PolicyHolderDto holderDto = this.mapper.map(dbPolicyHolder, PolicyHolderDto.class);
			ResponceStructure<PolicyHolderDto> structure = new ResponceStructure<>();
			structure.setMassege("Policy Holder Deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(holderDto);
			return new ResponseEntity<ResponceStructure<PolicyHolderDto>>(structure, HttpStatus.GONE);
		} else {
			throw new PolicyHolderNotPresent("Sorry ! no PolicyHolder Present In Db");
		}
	}

	public List<Policy> getAllPolicyById(int policyId) {
		List<Policy> list = new ArrayList<>();
		PolicyHolder dbHolder = holderdao.findPolicyHolderById(policyId);
		if (dbHolder != null) {
			List<Policy> policies = dbHolder.getPolicies();

			for (Policy policy : policies) {
				list.add(policy);
			}
		} else {
			throw new PersonNotBuyAnyPolicyException("Sorry ! this Person Not Buy any Policy");
		}
		return list;
	}

	public ResponseEntity<ResponceStructure<PolicyHolderDto>> logInPolicyHolderByEmailById(String email,
			String password) {

		PolicyHolder policyHolder = holderdao.findPolicyHolderByEmailId(email);
		if (policyHolder != null) {
			if (policyHolder.getPassword().equals(password)) {

				PolicyHolderDto policyHolderDto = this.mapper.map(policyHolder, PolicyHolderDto.class);

				ResponceStructure<PolicyHolderDto> structure = new ResponceStructure<>();
				structure.setMassege("PolicyHolder Login Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(policyHolderDto);
				return new ResponseEntity<ResponceStructure<PolicyHolderDto>>(structure, HttpStatus.CREATED);

			} else {
				throw new PasswordMissMatchException("Worng Password ! Write again");
			}

		} else {
			throw new EmailIdNotFountException("Invalid Email Please Check It again");
		}

	}

}
