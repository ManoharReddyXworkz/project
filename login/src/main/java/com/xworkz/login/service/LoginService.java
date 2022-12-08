package com.xworkz.login.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.xworkz.login.dto.LoginDTO;

public interface LoginService {

	default Boolean validateAndSave(LoginDTO loginDTO) {
		return true;
	}

	default List<LoginDTO>  findByEmail(String newEmail) {
		return null;
	}

	default Boolean sendMail(String email, LoginDTO loginDTO, String security) {
		return null;

	}

	default List<LoginDTO> findByEmailAndSecurity(String email, String security, LoginDTO loginDTO) {
		return null;
	}

	default String password() {
		return null;
	}

	default Boolean updateStatusByEmail(String email, String status) {
		return true;
	}

	default Boolean updateCountByEmail(Integer count, String email) {
		return true;
	}

	default Integer otpGeneration() {
		return null;
	}

	default Boolean resetPasswordByEmail(String email, String security, Integer otp, LoginDTO loginDTO) {
		return true;
	}

	default Boolean updateOtpDateAndTimeByEmail(String email, LoginDTO loginDTO) {
			return true;
	 }
	
	default Boolean sendOTPMail(String otpMail, Integer otp, LoginDTO loginDTO) {
		return true;
}
}