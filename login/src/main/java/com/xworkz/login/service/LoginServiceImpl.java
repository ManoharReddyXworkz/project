package com.xworkz.login.service;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.login.dao.LoginDAO;
import com.xworkz.login.dto.LoginDTO;

@Service
public class LoginServiceImpl implements LoginService {

	Integer count = 0;
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public Boolean validateAndSave(LoginDTO loginDTO) {
		ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
		Validator validate = validator.getValidator();
		Set<ConstraintViolation<LoginDTO>> set = validate.validate(loginDTO);
		if (set.size() > 0) {
			System.out.println("Data Entered  Are Invalid");
		} else {
			System.out.println("Data Entered  Are valid");
			String passwordGenarator = password();
			String status = "unblocked";
			Integer count = 0;
			if (!passwordGenarator.isEmpty()) {
				System.out.println("Password is Not Empty");
				loginDTO.setSecurity(passwordGenarator);
				loginDTO.setStatus(status);
				loginDTO.setCount(count);
				Boolean save = loginDAO.save(loginDTO);
				System.out.println(save);
				if (save) {
					Boolean sendEmail = loginDAO.sendEmail(loginDTO.getUserEmail(), loginDTO);
					System.out.println(sendEmail);
				}
			}

		}
		return true;
	}

	@Override
	public List<LoginDTO> findByEmail(String newEmail) {
		System.out.println("Running FindByEmail of service");
		List<LoginDTO>findByEmail = loginDAO.findByEmail(newEmail);
		System.out.println(findByEmail);
		if (findByEmail != null) {
			return findByEmail;
		} else {

			return null;

		}
	}
	
	@Override
	public Boolean sendMail(String email, LoginDTO loginDTO, String security) {
		String to = email;

		String from = "hanamantxworkz@outlook.com";

		String host = "smtp.office365.com";

		String password = "hanamant123@";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		// properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);

			}

		});
		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Registration Confirmation  Mail");
			message.setText("Hi" + " " + loginDTO.getUserName() + " " + "your  Registration is completed" + " " + " "
					+ "Your Password" + " " + loginDTO.getSecurity() + " " + "Thank You");

			System.out.println("Sending........");
			

			Transport.send(message);

			System.out.println("Sent message Succesfully");
			
		} catch (MessagingException e) {
			e.printStackTrace();
			
		}

		return true;
	}
	
	

	@Override
	public List<LoginDTO> findByEmailAndSecurity(String email, String security, LoginDTO loginDTO) {
		List<LoginDTO> findByEmailAndSecurity = loginDAO.findBYEmailAndSecurity(email, security);
		if (findByEmailAndSecurity != null) {
			this.count = 0;
			this.loginDAO.updateCountByEmail(count, email);
			return findByEmailAndSecurity;
		} else {
			this.count++;
			System.out.println("Checking the password" + this.count);

		}
		if (this.count <= 3) {
			this.loginDAO.updateCountByEmail(count, email);
		}
		if (this.count >= 3) {
			System.out.println("Checking the condtion" + this.count);
			loginDTO.setStatus("Blocked");
			loginDAO.updateStatusByEmail(email, loginDTO.getStatus());
		}
		return findByEmailAndSecurity;

	}

	@Override
	public String password() {
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghizklmnopqrstuvwxyz";
		String num = "0123456789";
		String specialChars = "!@#$%&*";
		String combination = upperCase + lowerCase + num + specialChars;
		int length = 12;
		char[] password = new char[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			password[i] = combination.charAt(random.nextInt((combination.length())));
		}
		return new String(password);
	}

	@Override
	public Boolean updateStatusByEmail(String email, String status) {
		System.out.println("Running updateStatusByEmail");
		Boolean updateCountByEmail = loginDAO.updateStatusByEmail(email, status);
		System.out.println(updateCountByEmail);
		return true;
	}

	@Override
	public Boolean updateCountByEmail(Integer count, String email) {
		System.out.println("Running updateCountByEmail");
		Boolean updateCountByEmail = loginDAO.updateCountByEmail(count, email);
		System.out.println(updateCountByEmail);
		return true;
	}

	@Override
	public Integer otpGeneration() {
		System.out.println("Calling otp generator");
		int randomPin = (int) (Math.random() * 9000) + 1000;
		Integer otp = Integer.valueOf(randomPin);
	    System.out.println(otp);
		return otp;
	}

	
}