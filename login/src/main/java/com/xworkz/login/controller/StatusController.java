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
import com.xworkz.login.service.LoginService;

@Controller
@RequestMapping("status")
public class StatusController {
 Integer count = 0;
 
 @Autowired
 @Qualifier("serviceImpl")
 private LoginService loginService;
 
 public StatusController() {
	System.out.println("Created"+this.getClass().getSimpleName());
}
 @PostMapping
 public String signIn(Model model,LoginDTO loginDTO) {
	return onFindByNameAndSecurity(loginDTO,model);
	 
 }
 @GetMapping
 public String onFindByNameAndSecurity(LoginDTO loginDTO, Model model) {
	 
	 System.out.println("Running onFindByNameAndSecurity method ");
		List<LoginDTO> emailAndMobileNumber = loginService.findByEmailAndSecurity(loginDTO.getUserEmail(),
				loginDTO.getSecurity(), loginDTO);
		if (emailAndMobileNumber == null) {
			model.addAttribute("message", "Incorrect credentials Entered");
			count++;
			System.out.println(count);
			if (count == 4) {
				model.addAttribute("message", "Your account has be blocked");
			}
		} else {
			if (emailAndMobileNumber.get(0).getStatus().equals("Blocked")) {
				model.addAttribute("message", "Your account has be blocked");
				return "Signin";
			} else {
				model.addAttribute("message", "Welcome To You Portfolio");
				return "Home";
			}

		}
		return "Signin";

 }
}