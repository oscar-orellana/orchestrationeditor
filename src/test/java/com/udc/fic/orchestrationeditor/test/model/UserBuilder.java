package com.udc.fic.orchestrationeditor.test.model;

import java.util.Date;

import com.udc.fic.orchestrationeditor.model.User;
import com.udc.fic.orchestrationeditor.model.UserRole;

public class UserBuilder {
	
	private User user;

	public UserBuilder() {	
		this.user = new User();
	}
	
	public UserBuilder id(long id) {
		user.setId(id);
		return this;
	}
	
	public UserBuilder username(String username) {
		user.setUsername(username);
		return this;
	}
	
	public UserBuilder password(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder role(UserRole role) {
		user.setRole(role);
		return this;
	}
	
	public UserBuilder firstName(String firstName) {
		user.setFirstName(firstName);
		return this;
	}
	
	public UserBuilder lastName(String lastName) {
		user.setLastName(lastName);
		return this;
	}
	
	public UserBuilder birthDate(Date birthDate) {
		user.setBirthDate(birthDate);
		return this;
	}
	
	public UserBuilder email(String email) {
		user.setEmail(email);
		return this;
	}
	
	public UserBuilder phoneNumber(String phoneNumber) {
		user.setPhoneNumber(phoneNumber);
		return this;
	}
	
	public User build() {
		return user;
	}

}
