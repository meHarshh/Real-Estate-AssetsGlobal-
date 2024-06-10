package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.ServiceRequest;
import org.assetsglobal.dto.ServiceResponse;
import org.assetsglobal.entity.Service;
import org.assetsglobal.repository.ServiceRepository;
import org.assetsglobal.service.ServiceService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ResponseStructure<ServiceResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponse>> registerServiceUser(ServiceRequest request) {

		Service service = serviceRepository.save(mapToService(request));
		ServiceResponse response = mapToResponse(service);
		return ResponseEntity.ok(responseStructure.setData(response).setMessage(
				"Our executives will be getting back to you as you want to " + service.getServiceType().toString())
				.setStatusCode(HttpStatus.OK.value()));
	}

	private ServiceResponse mapToResponse(Service service) {

		return ServiceResponse.builder().serviceId(service.getServiceId()).name(service.getName())
				.email(service.getEmail()).phoneNumber(service.getPhoneNumber()).message(service.getMessage())
				.serviceType(service.getServiceType()).build();
	}

	private Service mapToService(ServiceRequest request) {
		Service service = new Service();
		service.setName(request.getName());
		service.setEmail(request.getEmail());
		service.setMessage(request.getMessage());
		service.setPhoneNumber(request.getPhoneNumber());
		service.setServiceType(request.getServiceType());
		return service;
	}

}
