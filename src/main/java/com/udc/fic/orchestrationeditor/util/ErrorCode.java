package com.udc.fic.orchestrationeditor.util;

public enum ErrorCode {
	DUPLICATED_USER("409-0"),
	USER_NOT_FOUND("404-0"),
	INPUT_VALIDATION("400-0");

	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
