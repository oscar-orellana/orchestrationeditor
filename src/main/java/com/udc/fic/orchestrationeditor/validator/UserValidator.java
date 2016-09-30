package com.udc.fic.orchestrationeditor.validator;

import org.springframework.stereotype.Component;
import com.udc.fic.orchestrationeditor.dto.UserDto;
import com.udc.fic.orchestrationeditor.exception.InputValidationException;

@Component
public class UserValidator {

	public void validate(UserDto userDto) throws InputValidationException {
		if (userDto.getUsername() == null)
			throw new InputValidationException("username is required");
		if (userDto.getPassword() == null)
			throw new InputValidationException("password is required");
		if (userDto.getRole() == null)
			throw new InputValidationException("role is required");
	}
}