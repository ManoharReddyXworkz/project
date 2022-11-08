package com.xworkz.login.dao;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xworkz.login.dto.LoginDTO;

import io.quarkus.arc.runtime.BeanContainer.Factory;

@Repository
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Override
	public Boolean save(LoginDTO loginDTO) {
		manager = factory.createEntityManager();
		try {
			
			EntityTransaction transaction= manager.getTransaction();
			transaction.begin();
			manager.persist(loginDTO);
			transaction.commit();
		}
		catch (PersistenceException p) {
			p.printStackTrace();
		}
		finally {
			manager.close();
		}
		
		return true;
	}

	@Override
	public List<LoginDTO> FindByEmail(String newEmail) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery( "FindByEmail");
			query.setParameter("email", newEmail);
			List<LoginDTO> resultList = query.getResultList();
			System.out.println(resultList);

			if(resultList.isEmpty()) {
				return null;
			}
				else if(!resultList.isEmpty()) {
					return resultList;
				}
		}
		catch (PersistenceException e) {
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean sendEmail(String email, LoginDTO loginDTO) {
	
		String name =  loginDTO.getUserName();
		
		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = "hanamantxworkz@outlook.com";

		// Assuming you are sending email from through OutLook smtp
		String host = "smtp.office365.com";

		// password for the Mail
		String pass = "hanamant123@";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.transport.protocol","smtp");
		

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, pass);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Registration Confirmation Mail");

			// Now set the actual message
			message.setText("Hi"+" "+ name
			+"This Is To Confirm You That Your Registration Was Successful"+" "
			+ "Thank You");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return null;
	}

	

}
