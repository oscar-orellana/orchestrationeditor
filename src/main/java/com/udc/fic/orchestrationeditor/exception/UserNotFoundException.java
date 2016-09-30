package com.udc.fic.orchestrationeditor.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 3613582031641812926L;

	public UserNotFoundException(long id) {
		super("User with id: " + id + " not found.");
	}
	
	public UserNotFoundException(String username) {
		super("User with username: " + username + " not found.");
	}
}
