package com.xworkz.login.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.login.dao.LoginDAO;
import com.xworkz.login.dto.LoginDTO;

@Service
public class UpdateOTPLoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private ServiceImpl serviceImpl;

	private LocalDateTime mailSendTimeAndDate;

	public UpdateOTPLoginServiceImpl() {
		System.out.println("Created" + this.getClass().getSimpleName());
	}

	@Override
	public Boolean updateOtpDateAndTimeByEmail(String email, LoginDTO loginDTO) {
		System.out.println("Running updateOtpByEmail");
		List<LoginDTO>findByEmail = loginDAO.findByEmail(email);
		System.out.println(findByEmail);
		if (findByEmail != null) {
			System.out.println("Email is matched");
			Integer otpGeneration = serviceImpl.otpGeneration();
			mailSendTimeAndDate = LocalDateTime.now();
			LocalDate dates = mailSendTimeAndDate.toLocalDate();
			LocalTime times = mailSendTimeAndDate.toLocalTime();
			System.out.println(dates);
			System.out.println(times);
			System.out.println(mailSendTimeAndDate);
			System.out.println(otpGeneration);
			loginDTO.setOtp(otpGeneration);
			loginDTO.setDate(dates);
			loginDTO.setTime(times);
			Boolean updateOtpByEmail = loginDAO.updateOtpDateAndTimeByEmail(loginDTO.getOtp(), loginDTO.getUserEmail(),
					loginDTO.getDate(), loginDTO.getTime());
			if (updateOtpByEmail) {
				boolean sendMail = serviceImpl.sendOTPMail(email, otpGeneration, loginDTO);
				if (sendMail) {
					System.out.println("OTP is sent Succesfully");
				} else {
					System.out.println("OTP is not Sent");
				}
			} else {

				System.out.println("Email is In Valid");
			}
		} else {

			System.out.println("Email is Not matched");
			return false;
		}
		return true;

	}

	@Override
	public Boolean resetPasswordByEmail(String email, String security, Integer otp, LoginDTO loginDTO) {
		System.out.println("Running resetPasswordByEmail");
		System.out.println("%%%%%%%%%%%%%");
		List<LoginDTO> findByEmail = loginDAO.findByEmail(email);
		System.out.println(findByEmail);
		if (findByEmail != null) {
			Integer otp1 = findByEmail.get(0).getOtp();
			LocalTime times = findByEmail.get(0).getTime();
			System.out.println(otp1);
			LocalTime otpEnterTime = LocalTime.now();
			System.out.println(otpEnterTime);
			Duration between = Duration.between(times, otpEnterTime);
			Long time = between.toMinutes();
			if (time <= 3) {
				if (otp1.equals(otp)) {
					System.out.println("Comparing otp2 with otp");
					loginDTO.setSecurity(security);
					loginDTO.setStatus("unBlocked");
					Boolean resetPasswordByEmail = loginDAO.resetPasswordByEmail(email, loginDTO.getStatus(),loginDTO.getSecurity());
					if (resetPasswordByEmail) {
						System.out.println("Resetpassword");
					} else {
						System.out.println("Otp is Not Match");
					}
				}
			} else {
				System.out.println("OTP is Expired");
			}
		} else {
			System.out.println("Email is Not Matched");
			return false;
		}

		return true;
	}

	@Override
	public Boolean sendOTPMail(String userEmail, Integer otp, LoginDTO loginDTO) {
		String to = userEmail;
		
		Integer  otpPassword = otp;

		String from = "sharathhs777@outlook.com";

		String host = "smtp.office365.com";

		String password = "sharathhs@12345";

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
			message.setSubject("OTP For Reset Password");
			message.setText(
					"Hi" + " " + "your OTP for Password Reset is" + " " + " " + otpPassword + " " + "Thank You");

			System.out.println("Sending........");

			Transport.send(message);

			System.out.println("Sent message Succesfully");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return true;
	}
}