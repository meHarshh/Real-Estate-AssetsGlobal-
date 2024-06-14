package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.HomeLoanRequest;
import org.assetsglobal.dto.HomeLoanResponse;
import org.assetsglobal.entity.HomeLoan;
import org.assetsglobal.exception.IllegalArgumentException;
import org.assetsglobal.repository.HomeLoanRepository;
import org.assetsglobal.service.HomeLoanService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HomeLoanServiceImpl implements HomeLoanService {

	@Autowired
	private HomeLoanRepository homeLoanRepository;

	@Autowired
	private ResponseStructure<HomeLoanResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<HomeLoanResponse>> addLoanLead(HomeLoanRequest homeLoanRequest) {
		validate(homeLoanRequest);

		HomeLoan homeLoan = homeLoanRepository.save(mapToEntity(homeLoanRequest));
		HomeLoanResponse response = mapToResponse(homeLoan);

		return ResponseEntity.ok(responseStructure.setData(response)
				.setMessage("Hey " + response.getName() + " you will be connected by our executive shortly")
				.setStatusCode(HttpStatus.OK.value()));
	}

	private void validate(HomeLoanRequest homeLoanRequest) {
		if (homeLoanRequest.getEmail() == null || homeLoanRequest.getEmail() == "")
			throw new IllegalArgumentException("Email cant be empty");

		if (homeLoanRequest.getName() == null || homeLoanRequest.getName() == "")
			throw new IllegalArgumentException("Name field cant be empty");

		if (String.valueOf(homeLoanRequest.getPhoneNumber()).length() != 10)
			throw new IllegalArgumentException("Mobile number is not valid please enter a 10 digit mobile number");

	}

	private HomeLoanResponse mapToResponse(HomeLoan homeLoan) {
		return HomeLoanResponse.builder()
				.loadId(homeLoan.getLoadId())
				.bankingPartner(homeLoan.getBankingPartner())
				.phoneNumber(homeLoan.getPhoneNumber())
				.email(homeLoan.getEmail())
				.name(homeLoan.getName())
				.build();

	}

	private HomeLoan mapToEntity(HomeLoanRequest homeLoanRequest) {
		HomeLoan homeLoan = new HomeLoan();
		homeLoan.setName(homeLoanRequest.getName());
		homeLoan.setEmail(homeLoanRequest.getEmail());
		homeLoan.setPhoneNumber(homeLoanRequest.getPhoneNumber());
		homeLoan.setBankingPartner(homeLoanRequest.getBankingPartner());
		return homeLoan;
	}

}
