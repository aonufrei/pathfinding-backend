package com.aonufrei.pathfindingapp.pathfinding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point2d {

	private Integer x;

	private Integer y;

	@JsonIgnore
	public Point2d copy() {
		return new Point2d(x, y);
	}

	public Point2d withX(Integer x) {
		this.x = x;
		return this;
	}

	public Point2d withY(Integer y) {
		this.y = y;
		return this;
	}

}
