package org.assetsglobal.service;

import org.assetsglobal.dto.HomeLoanRequest;
import org.assetsglobal.dto.HomeLoanResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface HomeLoanService {

	ResponseEntity<ResponseStructure<HomeLoanResponse>> addLoanLead(HomeLoanRequest homeLoanRequest);

}
