package org.assetsglobal.service;

import org.assetsglobal.dto.UserRequest;
import org.assetsglobal.dto.UserResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);

}
