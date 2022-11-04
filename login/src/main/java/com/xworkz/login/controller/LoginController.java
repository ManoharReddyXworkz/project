package com.xworkz.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.login.dto.LoginDTO;
import com.xworkz.login.service.LoginService;

@Controller
@RequestMapping("/login")

public class LoginController {

	@Autowired
	private LoginService  loginService;

	@PostMapping
	public String onSave(LoginDTO loginDTO,Model model) {
		System.out.println("Calling onSave Method");
		System.out.println(loginDTO);
		if(loginDTO !=null) {
			Boolean findByEmail = loginService.FindByEmail(loginDTO.getUserEmail());
			System.out.println(findByEmail);
			if(!findByEmail) {
				model.addAttribute("msg", "Email is allready is exist");
			}
			else {
				Boolean validateAndSave = loginService.validateAndSave(loginDTO);
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