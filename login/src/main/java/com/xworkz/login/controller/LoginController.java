package com.xworkz.login.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.login.dto.LoginDTO;
import com.xworkz.login.service.LoginServiceImpl;

@Controller
@RequestMapping("login")
public class LoginController {

	
	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginServiceImpl  loginseviceImpl;
 
    @PostMapping
	public String onSave(LoginDTO loginDTO,Model model) {
		System.out.println("Calling onSave Method");
		System.out.println(loginDTO);
		if(loginDTO != null) {
			List<LoginDTO>findByEmail = loginseviceImpl.findByEmail(loginDTO.getUserEmail());
			System.out.println(findByEmail);
			if(findByEmail != null) {
				model.addAttribute("msg", "Email is allready is exist");
			}
			else {
				Boolean validateAndSave = loginseviceImpl.validateAndSave(loginDTO);
				System.out.println("validateAndSave");
				if(validateAndSave) {
					model.addAttribute("DTO",loginDTO);
					model.addAttribute("mesg", "validateAndSave");

				}
				else {
					model.addAttribute("msg","Data Entered Are not valid");
				}

			}

		}
		return "SignUp";
	}
	

	 
}