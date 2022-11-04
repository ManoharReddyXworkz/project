package com.xworkz.login.service;

import com.xworkz.login.dto.LoginDTO;

public interface LoginService {

	public Boolean validateAndSave(LoginDTO loginDTO);
	Boolean FindByEmail(String newEmail);
}
