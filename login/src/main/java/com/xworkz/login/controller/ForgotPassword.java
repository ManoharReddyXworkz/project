package com.xworkz.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.login.dto.LoginDTO;
import com.xworkz.login.service.ServiceImpl;
import com.xworkz.login.service.UpdateOTPLoginServiceImpl;

@Controller
@RequestMapping("forgot")
public class ForgotPassword {
	
@Autowired
private ServiceImpl  seviceImpl;

@Autowired
@Qualifier("updateOTPLoginServiceImpl")
private UpdateOTPLoginServiceImpl updateOTPLoginServiceImpl;

public ForgotPassword() {
	System.out.println("created"+this.getClass().getSimpleName());
	
}
@PostMapping
public String Password(Model model,LoginDTO loginDTO) {
	return forgotPassword(model, loginDTO);
}

@GetMapping
public String forgotPassword(Model model, LoginDTO loginDTO) {
	List<LoginDTO> findByEmail = seviceImpl.findByEmail(loginDTO.getUserEmail());
	System.out.println(findByEmail);
	if(findByEmail != null) {
		Boolean updateOtpDateAndTimeByEmail = updateOTPLoginServiceImpl.updateOtpDateAndTimeByEmail(loginDTO.getUserEmail(), loginDTO);
		if(updateOtpDateAndTimeByEmail) {
		model.addAttribute("Msg", "Plz Enter your New Password Below And OTP");
		return "ConformPassword";
		}
	}
	else {
	model.addAttribute("message", "Your entered wrong email plz check ");
	return "forgot";
	}
	return "forgot";
}
}