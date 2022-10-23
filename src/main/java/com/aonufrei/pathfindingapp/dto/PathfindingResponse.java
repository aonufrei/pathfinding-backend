package com.aonufrei.pathfindingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PathfindingResponse<T> {

	private boolean found = false;

	private String message;

	private T details;

}
