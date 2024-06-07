package org.assetsglobal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder 
public class ContactFormResponse {

	private int contactId;
	private String name;
	private String email;
	private String message;
	private long mobileNumber;
}
