package org.assetsglobal.dto;

import org.assetsglobal.enums.ServiceType;

import lombok.Data;

@Data
public class ServiceRequest {
	private String name;
	private String email;
	private long phoneNumber;
	private String message;
	private ServiceType serviceType;

}
