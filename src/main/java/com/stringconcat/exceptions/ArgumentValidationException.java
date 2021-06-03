package com.stringconcat.exceptions;

public class ArgumentValidationException extends RuntimeException{
	private static final long serialVersionUID = -5807240081670917953L;

	public ArgumentValidationException(String message) {
		super(message);
	}
}
