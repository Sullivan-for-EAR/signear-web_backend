package com.signear.application.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -4034643355190972127L;
	private ExceptionEnum error;

	public ApiException(ExceptionEnum e) {
		super(e.getMessage());
		this.error = e;
	}
}
