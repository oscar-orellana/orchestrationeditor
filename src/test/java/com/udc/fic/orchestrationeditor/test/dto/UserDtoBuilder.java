package com.udc.fic.orchestrationeditor.test.dto;

import java.util.Date;

import com.udc.fic.orchestrationeditor.dto.UserDto;
import com.udc.fic.orchestrationeditor.model.UserRole;

public class UserDtoBuilder {
	
	private UserDto userDto;

	public UserDtoBuilder() {	
		this.userDto = new UserDto();
	}
	
	public UserDtoBuilder id(long id) {
		userDto.setId(id);
		return this;
	}
	
	public UserDtoBuilder username(String username) {
		userDto.setUsername(username);
		return this;
	}
	
	public UserDtoBuilder password(String password) {
		userDto.setPassword(password);
		return this;
	}
	
	public UserDtoBuilder role(UserRole role) {
		userDto.setRole(role);
		return this;
	}
	
	public UserDtoBuilder firstName(String firstName) {
		userDto.setFirstName(firstName);
		return this;
	}
	
	public UserDtoBuilder lastName(String lastName) {
		userDto.setLastName(lastName);
		return this;
	}
	
	public UserDtoBuilder birthDate(Date birthDate) {
		userDto.setBirthDate(birthDate);
		return this;
	}
	
	public UserDtoBuilder email(String email) {
		userDto.setEmail(email);
		return this;
	}
	
	public UserDtoBuilder phoneNumber(String phoneNumber) {
		userDto.setPhoneNumber(phoneNumber);
		return this;
	}
	
	public UserDto build() {
		return userDto;
	}

}
