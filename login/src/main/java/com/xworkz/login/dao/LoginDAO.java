package com.xworkz.login.dao;

import java.util.List;

import com.xworkz.login.dto.LoginDTO;

public interface LoginDAO {

	public Boolean save(LoginDTO loginDTO);
	public List<LoginDTO> FindByEmail(String newEmail);
	 public Boolean sendEmail(String email, LoginDTO loginDTO);
}