package com.xworkz.login.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.login.dao.LoginDAO;
import com.xworkz.login.dto.LoginDTO;

@Service
public class ServiceImpl implements LoginService {
	
@Autowired
private LoginDAO loginDAO;


	@Override
	public Boolean validateAndSave(LoginDTO loginDTO) {
		ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
		 Validator validate = validator.getValidator();
		Set<ConstraintViolation<LoginDTO>> set = validate.validate(loginDTO);
		if(set.size()>0) {
			System.out.println("Detail Entered  Are Invalid");
			return false;
		}
		else {
			System.out.println("Detail Entered  Are Invalid");
			Boolean save = loginDAO.save(loginDTO);
			System.out.println(save);
			return true;
		}
		
	}


	@Override
	public Boolean FindByEmail(String newEmail) {
		System.out.println("Running FindByEmail");
		List<LoginDTO> findByEmail = loginDAO.FindByEmail(newEmail);
		System.out.println(findByEmail);
		if(findByEmail!=null) {
			return false;
		}
		else {
			return true;
		}
	}
	}


