package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.ContactFormRequest;
import org.assetsglobal.dto.ContactFormResponse;
import org.assetsglobal.entity.ContactForm;
import org.assetsglobal.exception.IllegalArgumentException;
import org.assetsglobal.mailservice.MailService;
import org.assetsglobal.mailservice.MessageModel;
import org.assetsglobal.repository.ContactFormRepository;
import org.assetsglobal.service.ContactFormService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class ContactFormServiceImpl implements ContactFormService {

	@Autowired
	private ContactFormRepository contactFormRepository;

	@Autowired
	private ResponseStructure<ContactFormResponse> responseStructure;

	@Autowired
	private MailService mailService;

//	This form is used to add the leads to the db and send the mail to the official of the assets global
	@Override
	public ResponseEntity<ResponseStructure<ContactFormResponse>> addClient(ContactFormRequest contactFormRequest) {
		validate(contactFormRequest);

		ContactForm contactForm = contactFormRepository.save(mapToContactForm(contactFormRequest));
		try {
			sendMail(contactForm);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		ContactFormResponse contactFormResponse = mapToContactFormResponse(contactForm);
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setData(contactFormResponse)
				.setMessage("Thanks! " + contactForm.getName()
						+ " you got registered successfully, we will shortly get connected to you"));
	}

	//this form is used for the validation of the contact form details coming from the user
	private void validate(ContactFormRequest contactFormRequest) {
		if (contactFormRequest.getName() == null || contactFormRequest.getName() == "")
			throw new IllegalArgumentException("Please enter the name");

		if (contactFormRequest.getEmail() == null || contactFormRequest.getEmail() == "")
			throw new IllegalArgumentException("Please enter the valid email");

		if ((String.valueOf(contactFormRequest.getMobileNumber()).length()) != 10)
			throw new IllegalArgumentException("Enter a valid 10 digits mobile number");
	}

	//this method is for sending the mail to Assets Global which contains the information of the lead
	private void sendMail(ContactForm contactForm) throws MessagingException {
		String htmlContent = "<html>" + "<head>" + "<style>" + "table {" + "    font-family: Arial, sans-serif;"
				+ "    border-collapse: collapse;" + "    width: 100%;" + "}" + "td, th {"
				+ "    border: 1px solid #dddddd;" + "    text-align: left;" + "    padding: 8px;" + "}"
				+ "tr:nth-child(even) {" + "    background-color: #f2f2f2;" + "}" + "h2 {" + "    color: #333333;" + "}"
				+ "</style>" + "</head>" + "<body>" + "<h2>Contact Form Submission</h2>" + "<table>" + "<tr>"
				+ "<td colspan='2' style='text-align:center;'>"
				+ "<img src='https://assetsglobal.in/images/img/logo.png' alt='Assets Global Logo' style='max-width:150px; height:auto;' />" // Adjusted
				+ "</td>" + "</tr>" + "<tr>" + "<td><strong>Name:</strong></td>" + "<td>" + contactForm.getName()
				+ "</td>" + "</tr>" + "<tr>" + "<td><strong>Mobile Number:</strong></td>" + "<td>"
				+ contactForm.getMobileNumber() + "</td>" + "</tr>" + "<tr>" + "<td><strong>Email:</strong></td>"
				+ "<td>" + contactForm.getEmail() + "</td>" + "</tr>" + "<tr>" + "<td><strong>Message:</strong></td>"
				+ "<td>" + contactForm.getMessage() + "</td>" + "</tr>" + "</table>" + "</body>" + "</html>";

		MessageModel model = MessageModel.builder().to("meharshhk@gmail.com").subject("Regarding Assets global lead")
				.text(htmlContent).build();
		mailService.sendMailMessage(model);
	}

	// This mapper method is built using the builder pattern which is used for the mapping of contact form to response 
	private ContactFormResponse mapToContactFormResponse(ContactForm contactForm) {
		return ContactFormResponse.builder()
				.contactId(contactForm.getContactId())
				.name(contactForm.getName())
				.email(contactForm.getEmail())
				.message(contactForm.getMessage())
				.mobileNumber(contactForm.getMobileNumber())
				.build();
	}

	// This contact form method is used to map the contact form from the request to the entity/bean
	private ContactForm mapToContactForm(ContactFormRequest contactFormRequest) {
		ContactForm contactForm = new ContactForm();

		if (contactFormRequest.getName() != null)
			contactForm.setName(contactFormRequest.getName());

		if (contactFormRequest.getEmail() != null) {
			contactForm.setEmail(contactFormRequest.getEmail());
		}
		if (contactFormRequest.getMessage() != null) {
			contactForm.setMessage(contactFormRequest.getMessage());
		}
		if (contactFormRequest.getMobileNumber() != 0) {
			contactForm.setMobileNumber(contactFormRequest.getMobileNumber());
		}
		return contactForm;
	}

}
