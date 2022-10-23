package com.aonufrei.pathfindingapp.exception;

public class PathfindingRequestValidationException extends RuntimeException {

	public PathfindingRequestValidationException(String message) {
		super(message);
	}

	public PathfindingRequestValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
