package com.virtualpairprogrammers.learningthymeleaf;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddUserModelValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return AddUserModel.class.equals(arg0);
	}

	@Override
	public void validate(Object user, Errors e) {
		AddUserModel addUserModel = (AddUserModel) user;
		if (!addUserModel.getPassword().equals(addUserModel.getRepeatPassword())) {
			e.rejectValue("repeatPassword", "PasswordsDontMatch");
		}

	}

}
