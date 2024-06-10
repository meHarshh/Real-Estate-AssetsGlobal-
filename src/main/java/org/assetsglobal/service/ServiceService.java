package org.assetsglobal.service;

import org.assetsglobal.dto.ServiceRequest;
import org.assetsglobal.dto.ServiceResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

	ResponseEntity<ResponseStructure<ServiceResponse>> registerServiceUser(ServiceRequest request);

}
