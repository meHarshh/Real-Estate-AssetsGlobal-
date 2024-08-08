package org.assetsglobal.controller;

import org.assetsglobal.dto.ContactFormRequest;
import org.assetsglobal.dto.ContactFormResponse;
import org.assetsglobal.service.ContactFormService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(value = "https://assetsglobal.in", allowCredentials = "true")
@RestController
public class ContactFormController {

	@Autowired
	private ContactFormService contactFormService;

	@PostMapping(value = "addLeads")
	public ResponseEntity<ResponseStructure<ContactFormResponse>> addClient(
			@RequestBody ContactFormRequest contactFormRequest) {
		return contactFormService.addClient(contactFormRequest);
	}
}
