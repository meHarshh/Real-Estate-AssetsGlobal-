package org.assetsglobal.dto;

import org.assetsglobal.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponse {
	private int userId;
	private String userName;
	private String userEmail;
	private UserRole userRole;

}
