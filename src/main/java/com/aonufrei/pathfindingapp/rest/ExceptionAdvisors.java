package com.aonufrei.pathfindingapp.rest;

import com.aonufrei.pathfindingapp.dto.BadRequestResponse;
import com.aonufrei.pathfindingapp.exception.PathfindingRequestValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisors {

	@ExceptionHandler({ PathfindingRequestValidationException.class })
	public ResponseEntity<BadRequestResponse> processRequestValidationError(Exception e) {
		return ResponseEntity.badRequest().body(new BadRequestResponse(e.getMessage()));
	}

}
