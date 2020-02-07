package com.cts.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("msg","I am a User");
		return "index";
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String getAdmin(ModelMap model ) {
		model.addAttribute("msgOne", "This is an Admin Page");
		model.addAttribute("msgTwo","This page requires Admin Permissions.");
		return "admin";
		
	}

}
