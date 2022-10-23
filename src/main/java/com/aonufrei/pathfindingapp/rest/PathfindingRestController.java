package com.aonufrei.pathfindingapp.rest;

import com.aonufrei.pathfindingapp.dto.GridMapPathfindingRequest;
import com.aonufrei.pathfindingapp.dto.PathfindingResponse;
import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.aonufrei.pathfindingapp.service.rest.PathfindingRestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/find-path")
@CrossOrigin(origins = "*")
public class PathfindingRestController {

	private final PathfindingRestService pathfindingRestService;

	public PathfindingRestController(PathfindingRestService pathfindingRestService) {
		this.pathfindingRestService = pathfindingRestService;
	}

	@PostMapping("grid")
	public PathfindingResponse<ShortestPath<Point2d>> findRoute(@RequestBody GridMapPathfindingRequest request) {
		return pathfindingRestService.getShortestPathInGridMap(request);
	}

}
