package com.aonufrei.pathfindingapp.dto;

import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GridMapPathfindingRequest {

	@JsonProperty("map_info")
	private GridMapInfo gridMapInfo;

	@JsonProperty("start_point")
	private Point2d startPoint;

	@JsonProperty("end_point")
	private Point2d endPoint;

}
