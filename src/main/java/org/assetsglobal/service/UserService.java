package org.assetsglobal.service;

import org.assetsglobal.dto.OtpRequest;
import org.assetsglobal.dto.UserRequest;
import org.assetsglobal.dto.UserResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.assetsglobal.utility.SimpleResponseStructure;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<SimpleResponseStructure> addUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest);

}
