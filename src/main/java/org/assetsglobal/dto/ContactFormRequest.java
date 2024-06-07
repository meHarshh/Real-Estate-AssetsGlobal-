package org.assetsglobal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ContactFormRequest {

	private String name;
	private String email;
	private String message;
	private long mobileNumber;
}
