package com.xworkz.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.login.dto.LoginDTO;
import com.xworkz.login.service.LoginService;

@Controller
@RequestMapping("conform")
public class ConformPasswordController {
	
@Autowired
@Qualifier("updateOTPLoginServiceImpl")
private LoginService loginService;
public ConformPasswordController() {
	System.out.println("Created"+this.getClass().getSimpleName());
}
@PostMapping
public String conformPassword(LoginDTO loginDTO,Model model) {
	return updatePassword(loginDTO,model);
}


@GetMapping
public String updatePassword(LoginDTO loginDTO,Model model) {
	Boolean resetPasswordByEmail = loginService.resetPasswordByEmail(loginDTO.getUserEmail(), loginDTO.getSecurity(),
			loginDTO.getOtp(), loginDTO);
	if (resetPasswordByEmail) {
		model.addAttribute("message", "Password is Updated plz login ");
		return "Signin";
	} else {
		model.addAttribute("message", "Password is  not Updated plz check ");
		return "ForgotPassword";
	}
	
}
}	