package com.phoenixjcam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController
{
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView signUpPage()
	{
		ModelAndView model = new ModelAndView();
		
		model.setViewName("register");

		return model;
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView register(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "passwordRepeat", required = true) String passwordRepeat
			)
	{
		ModelAndView model = new ModelAndView();
		
		// check given username and email if already exist in db
		// else add user to db and login to dashboard
		
		
		
		if (error != null)
		{
			model.addObject("error", "Username or email already exist in database.");
		}
		else if (username != null)
		{
			model.addObject("msg", "User account has been created successfully.");
		}
		
		//model.addObject("title", username);
		
		model.setViewName("login");

		return model;
	}
}
