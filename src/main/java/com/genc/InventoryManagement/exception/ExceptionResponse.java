package com.genc.InventoryManagement.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime date;
	private String message;
	public ExceptionResponse(LocalDateTime date, String message) {
		super();
		this.date = date;
		this.message = message;
	}
	public ExceptionResponse() {
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
