package com.aonufrei.pathfindingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestResponse {

	@JsonProperty("is_error")
	private boolean isError = true;

	private String message;

	public BadRequestResponse(String message) {
		this.message = message;
	}

}
