package org.assetsglobal.controller;

import org.assetsglobal.dto.ServiceRequest;
import org.assetsglobal.dto.ServiceResponse;
import org.assetsglobal.service.ServiceService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000" , allowCredentials = "true")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	@PostMapping(value = "registerServiceLeads")
	public ResponseEntity<ResponseStructure<ServiceResponse>> regsiterServiceUser(@RequestBody ServiceRequest request) {
		return serviceService.registerServiceUser(request);
	}
}
