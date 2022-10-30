package com.aonufrei.pathfindingapp.rest;

import com.aonufrei.pathfindingapp.dto.GridMapPathfindingRequest;
import com.aonufrei.pathfindingapp.dto.PathfindingResponse;
import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.pathfinding.enums.Priority;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.aonufrei.pathfindingapp.service.rest.PathfindingRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/find-path")
@Tag(name = "API")
@CrossOrigin(origins = "*")
public class PathfindingRestController {

	private final PathfindingRestService pathfindingRestService;

	public PathfindingRestController(PathfindingRestService pathfindingRestService) {
		this.pathfindingRestService = pathfindingRestService;
	}

	@PostMapping("grid")
	@Operation(summary = "Find shortest path on grid map", description = "Finds shortest path between two point on a grid-like map.")
	public PathfindingResponse<ShortestPath<Point2d>> findRoute(@RequestParam Priority priority, @RequestBody GridMapPathfindingRequest request) {
		return pathfindingRestService.getShortestPathInGridMap(priority, request);
	}

}
