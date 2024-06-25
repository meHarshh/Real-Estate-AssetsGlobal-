package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.UserRequest;
import org.assetsglobal.dto.UserResponse;
import org.assetsglobal.entity.Buyer;
import org.assetsglobal.entity.Seller;
import org.assetsglobal.entity.User;
import org.assetsglobal.enums.UserRole;
import org.assetsglobal.repository.UserRepository;
import org.assetsglobal.service.UserService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {

		if (userRequest.getUserEmail() == null || userRequest.getUserEmail() == "")
			throw new org.assetsglobal.exception.IllegalArgumentException("Email is required");

		if (userRequest.getUserName() == null || userRequest.getUserName() == "")
			throw new org.assetsglobal.exception.IllegalArgumentException("User Name is required");

		if (userRequest.getUserRole() == null)
			throw new org.assetsglobal.exception.IllegalArgumentException("Invalid user role");

		User user = mapToChildrenEntity(userRequest);
		userRepository.save(user);
		UserResponse userResponse = mapToUserResponse(user);

		return ResponseEntity.ok(responseStructure.setData(userResponse)
				.setMessage("User added as " + user.getUserRole()).setStatusCode(HttpStatus.OK.value()));
	}

	private UserResponse mapToUserResponse(User user) {

		return UserResponse.builder().userId(user.getUserId()).userName(user.getUserName())
				.userEmail(user.getUserEmail()).phoneNumber(user.getPhoneNumber()).userRole(user.getUserRole()).build();
	}

	@SuppressWarnings("unchecked")
	private <T extends User> T mapToChildrenEntity(UserRequest userRequest) {
		UserRole role = userRequest.getUserRole();
		User user = null;
		switch (role) {
		case SELLER -> user = new Seller();
		case BUYER -> user = new Buyer();
		default -> throw new IllegalArgumentException("Invalid Input!!!");
		}
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setUserName(userRequest.getUserName());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserRole(userRequest.getUserRole());
		return (T) user;
	}
}
