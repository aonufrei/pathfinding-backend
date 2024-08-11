package com.aonufrei.pathfindingapp.pathfinding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point2d {

	private Integer x;

	private Integer y;

	@JsonIgnore
	public Point2d copyWithXOffset(int value) {
		return copyWithOffset(value, 0);
	}

	@JsonIgnore
	public Point2d copyWithYOffset(int value) {
		return copyWithOffset(0, value);
	}

	@JsonIgnore
	public Point2d copyWithOffset(int x, int y) {
		return new Point2d(this.x + x, this.y + y);
	}

}
