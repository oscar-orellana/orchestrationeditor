package com.udc.fic.orchestrationeditor.exception;

public class DuplicatedUserException extends Exception {

	private static final long serialVersionUID = -7995027055889878376L;

	public DuplicatedUserException(String username) {
		super("User with username: " + username + " already exists.");
	}

}
