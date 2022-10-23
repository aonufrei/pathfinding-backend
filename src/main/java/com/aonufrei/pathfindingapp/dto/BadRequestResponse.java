package com.aonufrei.pathfindingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestResponse {

	private boolean isError = true;

	private String message;

	public BadRequestResponse(String message) {
		this.message = message;
	}

}
