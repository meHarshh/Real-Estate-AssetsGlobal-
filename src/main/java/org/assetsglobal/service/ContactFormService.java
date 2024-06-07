package org.assetsglobal.service;

import org.assetsglobal.dto.ContactFormRequest;
import org.assetsglobal.dto.ContactFormResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface ContactFormService {

	ResponseEntity<ResponseStructure<ContactFormResponse>> addClient(ContactFormRequest contactFormRequest);


}
