package com.udc.fic.orchestrationeditor.util;

public class ErrorResponse {

	private ErrorCode code;
	private String message;

	public ErrorResponse(ErrorCode code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorResponse() {
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + "]";
	}

}
