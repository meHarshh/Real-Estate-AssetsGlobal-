package org.assetsglobal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.assetsglobal.enums.ServiceType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse {

	private int serviceId;
	private String name;
	private String email;
	private long phoneNumber;
	private String message;
	private ServiceType serviceType;
	private LocalDate day;
	private LocalTime time;
}
