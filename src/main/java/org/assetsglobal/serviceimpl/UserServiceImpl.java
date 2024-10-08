package org.assetsglobal.serviceimpl;

import java.util.Random;
import org.assetsglobal.exception.IllegalArgumentException;
import org.assetsglobal.cache.CacheStore;
import org.assetsglobal.dto.OtpRequest;
import org.assetsglobal.dto.UserRequest;
import org.assetsglobal.dto.UserResponse;
import org.assetsglobal.entity.Brocker;
import org.assetsglobal.entity.Buyer;
import org.assetsglobal.entity.Seller;
import org.assetsglobal.entity.User;
import org.assetsglobal.enums.UserRole;
import org.assetsglobal.mailservice.MailService;
import org.assetsglobal.mailservice.MessageModel;
import org.assetsglobal.repository.UserRepository;
import org.assetsglobal.service.UserService;
import org.assetsglobal.utility.ResponseStructure;
import org.assetsglobal.utility.SimpleResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	@Autowired
	private MailService mailService;

	@Autowired
	private CacheStore<User> userCache;

	@Autowired
	private CacheStore<String> otpCache;

	@Override
	public ResponseEntity<SimpleResponseStructure> addUser(UserRequest userRequest) {

		if (userRequest.getUserEmail() == null || userRequest.getUserEmail() == "")
			throw new org.assetsglobal.exception.IllegalArgumentException("Email is required");

		if (userRequest.getUserName() == null || userRequest.getUserName() == "")
			throw new org.assetsglobal.exception.IllegalArgumentException("User Name is required");

		if (userRequest.getUserRole() == null)
			throw new org.assetsglobal.exception.IllegalArgumentException("Invalid user role");

//		Otp generation method
		String otp = generateOTP();

//		Mapping to entity
		User user = mapToChildrenEntity(userRequest);
		otpCache.add(user.getUserEmail(), otp);
		userCache.add(user.getUserEmail(), user);

		if (!(userRequest.getUserRole() == UserRole.BUYER)) {
//		Send the otp on the mail
			try {
				sendMail(otp, user);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SimpleResponseStructure simpleResponseStructure = SimpleResponseStructure.builder().build();
		if (userRequest.getUserRole() == UserRole.BUYER)
			simpleResponseStructure.setMessage("We are verfying, please hold");
		else
			simpleResponseStructure.setMessage("Verify the otp sent through the mail");

		simpleResponseStructure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(simpleResponseStructure);
	}

	
	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest) {
		User user = userCache.getData(otpRequest.getEmail());

		if (!(user.getUserRole() == UserRole.BUYER)) {

			if (otpCache.getData(otpRequest.getEmail()) == null) {
				throw new IllegalAccessError("Otp expired");
			}
			if (!otpCache.getData(otpRequest.getEmail()).equals(otpRequest.getOtp())) {
				throw new IllegalAccessError("Invalid Otp");
			}
		}
		if (user == null)
			throw new IllegalArgumentException("Registration session expired");
		user.setEmailVerified(true);

		userRepository.save(user);
		UserResponse userResponse = mapToUserResponse(user);
		
		try {
			responseMail(userResponse);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(responseStructure.setData(userResponse)
				.setMessage("Hey " + user.getUserName() + " you got registered as " + user.getUserRole())
				.setStatusCode(HttpStatus.OK.value()));
	}

	private void responseMail(UserResponse user) throws MessagingException {
		// TODO Auto-generated method stub
		String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; background-color: #ffffff;'>"
			    + "<div style='text-align: center; padding-bottom: 20px;'>"
			    + "<img src='https://assetsglobal.in/images/img/logo.png' style='height: 80px; width: auto;'>"
			    + "</div>"
			    + "<p style='font-size: 16px; color: #333;'>Hi <strong>" + user.getUserName() + "</strong>,</p>"
			    + "<p style='font-size: 16px; color: #333;'>Thank you for verifying your email address.</p>"
			    + "<p style='font-size: 16px; color: #333;'>We are excited to have you on board with AssetsGlobal. You can now continue exploring our property postings and enjoy all the features we offer.</p>"
			    + "<p style='font-size: 16px; color: #333;'>If you have any questions or need further assistance, please don't hesitate to reach out to our support team.</p>"
			    + "<p style='font-size: 16px; color: #333;'>Best Regards,</p>"
			    + "<p style='font-size: 16px; color: #333;'><strong>AssetsGlobal Team</strong></p>"
			    + "</div>";

		
		MessageModel model = MessageModel.builder().to(user.getUserEmail()).subject("Thank You for Verifying Your Email - Welcome to AssetsGlobal!").text(htmlContent)
				.build();

		mailService.sendMailMessage(model);
	}


	private void sendMail(String otp, User user) throws MessagingException {
		String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; background-color: #ffffff;'>"
				+ "<div style='text-align: center; padding-bottom: 20px;'>"
				+ "<img src='https://assetsglobal.in/images/img/logo.png' style='height: 100px; width: auto;'>"
				+ "</div>" + "<p style='font-size: 16px; color: #333;'>Hi <strong>" + user.getUserName()
				+ "</strong>,</p>"
				+ "<p style='font-size: 16px; color: #333;'>Thank you for showing interest in property posting with AssetsGlobal.</p>"
				+ "<p style='font-size: 16px; color: #333;'>Please verify your email address using the OTP provided below:</p>"
				+ "<div style='text-align: center; margin: 20px 0; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; background-color: #f0f8ff;'>"
				+ "<h1 style='color: #1e90ff; font-size: 32px; margin: 0;'>" + otp + "</h1>" + "</div>"
				+ "<p style='font-size: 16px; color: #333;'>Best Regards,</p>"
				+ "<p style='font-size: 16px; color: #333;'><strong>AssetsGlobal Team</strong></p>" + "</div>";

		MessageModel model = MessageModel.builder().to(user.getUserEmail()).subject("Verify Your OTP").text(htmlContent)
				.build();

		mailService.sendMailMessage(model);
	}

	private String generateOTP() {
		return String.valueOf(new Random().nextInt(1000, 9999));
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
		case BROCKER -> user = new Brocker();
		default -> throw new IllegalArgumentException("Invalid Input!!!");
		}
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setUserName(userRequest.getUserName());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserRole(userRequest.getUserRole());
		return (T) user;
	}

}
