package org.assetsglobal.controller;

import org.assetsglobal.dto.HomeLoanRequest;
import org.assetsglobal.dto.HomeLoanResponse;
import org.assetsglobal.service.HomeLoanService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/" , allowCredentials = "true")
@RestController
public class HomeLoanController {

	@Autowired
	private HomeLoanService homeLoanService;
	
	@PostMapping(value = "addLoanLeads")
	public ResponseEntity<ResponseStructure<HomeLoanResponse>> addLoanLead(@RequestBody HomeLoanRequest HomeLoanRequest){
		return homeLoanService.addLoanLead(HomeLoanRequest);
	}
}
