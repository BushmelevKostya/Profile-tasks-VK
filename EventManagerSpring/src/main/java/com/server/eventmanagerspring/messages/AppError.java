package com.server.eventmanagerspring.messages;

public class AppError {
	private int statusCode;
	private String message;
	
	public AppError(){}
	public AppError(int code, String message) {
		this.statusCode = code;
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
