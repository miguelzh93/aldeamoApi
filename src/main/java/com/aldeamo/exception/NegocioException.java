package com.aldeamo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 614279814559834860L;

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
