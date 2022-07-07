package me.eun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonContorller {
	
	@GetMapping("/accessError")
	public String accessDenied() {
		return "error/accessError";
	}
	
	@GetMapping("/customLogin")
	public String loginForm(String error, Model model) {
		if(error !=null) {
			System.out.println(error);
			model.addAttribute("error",error);
		}
		return "member/login";
	}
	
	@GetMapping("/customLogout")
	public String logout() {
		return "member/logout";
		
	}
}
