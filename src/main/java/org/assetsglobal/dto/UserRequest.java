package org.assetsglobal.dto;

import org.assetsglobal.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String userName;
	private String userEmail;
	private UserRole userRole;
	
}
