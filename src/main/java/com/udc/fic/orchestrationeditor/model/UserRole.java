package com.udc.fic.orchestrationeditor.model;

import java.io.Serializable;

public enum UserRole implements Serializable {
	USER("USER"),
	ADMIN("ADMIN");

	private String userRole;

	private UserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

}
