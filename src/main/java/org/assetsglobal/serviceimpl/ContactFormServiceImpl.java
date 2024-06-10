package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.ContactFormRequest;
import org.assetsglobal.dto.ContactFormResponse;
import org.assetsglobal.entity.ContactForm;
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

	@Override
	public ResponseEntity<ResponseStructure<ContactFormResponse>> addClient(ContactFormRequest contactFormRequest) {
		ContactForm contactForm = contactFormRepository.save(mapToContactForm(contactFormRequest));
		try {
			sendMail(contactForm);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		ContactFormResponse contactFormResponse = mapToContactFormResponse(contactForm);
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setData(contactFormResponse)
				.setMessage("Thanks for regsitering " + contactForm.getName()
						+ " you got registered successfully, our executives will be getting back to you soon"));
	}

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

	private ContactFormResponse mapToContactFormResponse(ContactForm contactForm) {
		return ContactFormResponse.builder().contactId(contactForm.getContactId()).name(contactForm.getName())
				.email(contactForm.getEmail()).message(contactForm.getMessage())
				.mobileNumber(contactForm.getMobileNumber()).build();
	}

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
