package com.xworkz.login.dao;

import java.time.LocalDate;
import java.time.LocalTime;
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

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Override
	public Boolean save(LoginDTO loginDTO) {
		manager = factory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(loginDTO);
			transaction.commit();
		} catch (PersistenceException p) {
			p.printStackTrace();
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public List<LoginDTO> findByEmail(String newEmail) {
		System.out.println("calling Find By email of DAO Method");
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("FindByEmail");
			query.setParameter("email", newEmail);
			List<LoginDTO> resultList = query.getResultList();
			System.out.println("Getting from result list"+resultList);
			if (resultList.isEmpty()) {
				System.out.println("Email is not found");
			} else if(!resultList.isEmpty()){
				return resultList;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean sendEmail(String email, LoginDTO loginDTO) {

		String name = loginDTO.getUserName();

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
		properties.put("mail.transport.protocol", "smtp");

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
			message.setText("Hi" + " " + name + "This Is To Confirm You That Your Registration Was Successful" + " "
					+ "Thank You");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return true;
	}

	public List<LoginDTO> findBYEmailAndSecurity(String email, String security) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByEmailAndSecurity");
			query.setParameter("emails", email);
			query.setParameter("secu", security);
			System.out.println(email);
			System.out.println(security);
			List<LoginDTO> resultSet = query.getResultList();
			System.out.println(resultSet.size());
			if (resultSet.isEmpty()) {
				System.out.println("Data is not there");
				return null;
			} else if (!resultSet.isEmpty()) {
				return resultSet;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean updateStatusByEmail(String email, String status) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateStatusByEmail");
			query.setParameter("mails", email);
			query.setParameter("statuss", status);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public Boolean updateCountByEmail(Integer count, String email) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateCountByEmail");
			query.setParameter("counts", count);
			query.setParameter("mails", email);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public Boolean updateOtpDateAndTimeByEmail(Integer otp, String email, LocalDate date, LocalTime time) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateOtpDateAndTimeByEmail");
			query.setParameter("ot", otp);
			query.setParameter("gmail", email);
			query.setParameter("da", date);
			query.setParameter("tm", time);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			manager.close();
		}
		
		return true;
	}

	@Override
	public Boolean resetPasswordByEmail(String email, String security, String status) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("resetPasswordByOtpAndEmail");
			query.setParameter("passs", security);
			query.setParameter("sts", status);
			query.setParameter("mass", email);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			manager.close();
		}
		return true;
	}

}