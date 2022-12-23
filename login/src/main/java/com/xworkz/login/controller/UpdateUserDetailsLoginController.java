package com.xworkz.login.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.login.dto.LoginDTO;
import com.xworkz.login.service.UpdateUserDetailsLoginServiceImpl;

@Controller
@RequestMapping("update")
public class UpdateUserDetailsLoginController {
	@Autowired
	private UpdateUserDetailsLoginServiceImpl updateUserDetailsLoginServiceImpl;

	public UpdateUserDetailsLoginController() {
		System.out.println("created" + this.getClass().getName());
	}

	@PostMapping
	public String UpdateUserDetails(LoginDTO loginDTO, Model model, @RequestParam("file") MultipartFile file,
			String email) throws IOException {
		System.out.println("Running UpdateUserDetails");
		byte[] bytes = file.getBytes();
		String files = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path path = Paths.get("F://temp-files/" + files);
		System.out.println(path);
		Files.write(path, bytes);
		loginDTO.setFileName(files);
		Boolean updateUserDetailsByEmail = updateUserDetailsLoginServiceImpl.UpdateUserDetailsByEmail(
				loginDTO.getUserName(), loginDTO.getContactInfo(), loginDTO.getUserEmail(), loginDTO.getFileName());
if(updateUserDetailsByEmail) {
	
	model.addAttribute("message", "your Entered Details are correct plz login");
	model.addAttribute("LoginDTO",loginDTO);
	System.out.println("Update details"+" "+loginDTO);
	return  "Signin";
}else {
		model.addAttribute("message", "Your  Entered Details are Not correct plz check");
		model.addAttribute("LoginDTO", loginDTO);
		return "EditDetails";
	}

}
}
