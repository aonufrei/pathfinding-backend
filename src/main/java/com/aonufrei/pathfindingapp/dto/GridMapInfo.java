package com.aonufrei.pathfindingapp.dto;

import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GridMapInfo {

	@JsonProperty("left_border")
	private Integer leftBorder;

	@JsonProperty("right_border")
	private Integer rightBorder;

	@JsonProperty("top_border")
	private Integer topBorder;

	@JsonProperty("bottom_border")
	private Integer bottomBorder;

	@JsonProperty("walls")
	private List<Point2d> walls = new ArrayList<>();

}
