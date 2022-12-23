package com.xworkz.login.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.Local;

import com.xworkz.login.dto.LoginDTO;

public interface LoginDAO {

	public Boolean save(LoginDTO loginDTO);

	public List<LoginDTO>findByEmail(String newEmail);

	public Boolean sendEmail(String email, LoginDTO loginDTO);

	List<LoginDTO> findBYEmailAndSecurity(String email, String security);

	Boolean updateStatusByEmail(String email, String status);

	Boolean updateCountByEmail(Integer count, String email);

	default Boolean updateOtpDateAndTimeByEmail(Integer otp, String email, LocalDate date, LocalTime time) {
		return true;
	}

	default Boolean resetPasswordByEmail(String email, String security, String status) {
		return true;
	}
	
	default Boolean UpdateUserDetailsByEmail (String name, String contactInfo, String email, String fileName) {
		return true;
	}
		
	}
	


