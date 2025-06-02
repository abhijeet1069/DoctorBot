package com.servion.doctorBot.exception;

public class TagNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

