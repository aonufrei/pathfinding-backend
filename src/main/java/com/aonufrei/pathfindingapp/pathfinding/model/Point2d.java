package com.aonufrei.pathfindingapp.pathfinding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Point2d {

	@NotNull(message = "x coordinate cannot be null")
	private Integer x;

	@NotNull(message = "y coordinate cannot be null")
	private Integer y;
}
