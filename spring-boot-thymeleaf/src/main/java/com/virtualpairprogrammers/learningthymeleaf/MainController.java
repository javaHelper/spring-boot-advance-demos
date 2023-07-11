package com.virtualpairprogrammers.learningthymeleaf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView homePage() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("username", "John Doe");
		model.put("id", 173);
		return new ModelAndView("homepage", "model", model);
	}
	
	@RequestMapping("/profile")
	public ModelAndView viewProfile() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("title", "Mr");
		model.put("firstName", "John");
		model.put("surname", "Doe");
		model.put("dateOfBirth", new GregorianCalendar(2006,3,9).getTime());
		model.put("description", "a <strong>fantastic</strong> Java Programmer");
		
		List<String> languages = new ArrayList<String>();
		languages.add("English");
		languages.add("French");
		languages.add("Spanish");
		languages.add("Danish");
		
		model.put("languages", languages);
		
		model.put("color", "#ccc");
		
		return new ModelAndView("profile", "model", model);
	}
	
	@RequestMapping("/addUser") 
	public ModelAndView addUser() {
		return new ModelAndView("newUser", "addUserModel" , new AddUserModel());
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveUser(@Valid AddUserModel addUserModel, BindingResult bindingResult) {
		System.out.println("Add User Model "+ addUserModel);
		
		new AddUserModelValidator().validate(addUserModel, bindingResult);
		if (bindingResult.hasErrors()) {
			return "newUser";
		}
		return "userAdded";
	}
	
}
