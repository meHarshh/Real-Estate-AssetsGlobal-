package org.assetsglobal.controller;

import org.assetsglobal.dto.OtpRequest;
import org.assetsglobal.dto.UserRequest;
import org.assetsglobal.dto.UserResponse;
import org.assetsglobal.service.UserService;
import org.assetsglobal.utility.ResponseStructure;
import org.assetsglobal.utility.SimpleResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "addUser")
	public ResponseEntity<SimpleResponseStructure> addUser(@RequestBody UserRequest userRequest){
		return userService.addUser(userRequest);	
	} 
	
	@PostMapping("verifyotp")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(@RequestBody OtpRequest otpRequest){
		return userService.verifyOTP(otpRequest);
	} 
}
