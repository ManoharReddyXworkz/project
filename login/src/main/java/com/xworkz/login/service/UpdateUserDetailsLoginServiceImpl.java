package com.xworkz.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.login.dao.LoginDAO;
import com.xworkz.login.dto.LoginDTO;

@Service
public class UpdateUserDetailsLoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	public UpdateUserDetailsLoginServiceImpl() {
		System.out.println("Created" + this.getClass().getName());
	}

	@Override
	public Boolean UpdateUserDetailsByEmail(String name, String contactInfo, String email, String fileName) {
		System.out.println("Running UpdateUserDetailsByEmail");
		if (name != null && name.length() > 3 && name.length() < 30 && contactInfo != null && email != null
				&& email.length() > 3 && email.length() < 30 && email.endsWith("@gmail.com")) {
			System.out.println("Entered details are valid");
	List<LoginDTO> findByEmail = loginDAO.findByEmail(email);
	if (findByEmail != null) {

		Boolean UpdateUserDetailsByEmai = loginDAO.UpdateUserDetailsByEmail(name, contactInfo, email,
				fileName);
		if (UpdateUserDetailsByEmai) {
			System.out.println("UpdateUserDetailsByEmai is Updated");
		} else {
			System.out.println("UpdateUserDetailsByEmai is Not Updated");
		}
	}else {
		System.out.println("User data is invalid");
	}
}
else {
	System.out.println("Email is mismatched");
	return false;
}
return true;
}
}