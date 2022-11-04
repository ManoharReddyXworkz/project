package com.xworkz.login.dao;

import java.util.List;

import com.xworkz.login.dto.LoginDTO;

public interface LoginDAO {

	public Boolean save(LoginDTO loginDTO);
	 List<LoginDTO> FindByEmail(String newEmail);
}
