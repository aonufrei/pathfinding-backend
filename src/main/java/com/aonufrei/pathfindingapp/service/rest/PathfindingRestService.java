package com.aonufrei.pathfindingapp.service.rest;

import com.aonufrei.pathfindingapp.dto.GridMapInfo;
import com.aonufrei.pathfindingapp.dto.GridMapPathfindingRequest;
import com.aonufrei.pathfindingapp.dto.PathfindingResponse;
import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.exception.NoPathWasFoundException;
import com.aonufrei.pathfindingapp.exception.PathfindingRequestValidationException;
import com.aonufrei.pathfindingapp.pathfinding.algorithm.GridMapPathfindingAlgorithm;
import com.aonufrei.pathfindingapp.pathfinding.algorithm.PathFindingAlgorithm;
import com.aonufrei.pathfindingapp.pathfinding.enums.Priority;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.aonufrei.pathfindingapp.pathfinding.service.GridMapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Service
public class PathfindingRestService {

	private final static Logger log = LogManager.getLogManager().getLogger(PathfindingRestService.class.getName());
	private final PathFindingAlgorithm<Point2d> pathFindingAlgorithm;

	private final ObjectMapper mapper;

	public PathfindingRestService(GridMapPathfindingAlgorithm pathFindingAlgorithm, ObjectMapper mapper) {
		this.pathFindingAlgorithm = pathFindingAlgorithm;
		this.mapper = mapper;
	}

	public PathfindingResponse<ShortestPath<Point2d>> getShortestPathInGridMap(Priority priority, GridMapPathfindingRequest request) {
		validateGridMapPathfindingRequest(request);

		GridMapService gridMapService = new GridMapService(request.getGridMapInfo());

		PathfindingResponse<ShortestPath<Point2d>> response;
		try {
			ShortestPath<Point2d> shortestPath = pathFindingAlgorithm.findShortestPath(request.getStartPoint(),
					request.getEndPoint(), priority, gridMapService);
			response = PathfindingResponse.<ShortestPath<Point2d>>builder()
					.found(true)
					.message("The route was successfully found")
					.details(shortestPath)
					.build();
		} catch (NoPathWasFoundException e) {
			response = PathfindingResponse.<ShortestPath<Point2d>>builder()
					.found(false)
					.message(e.getMessage())
					.details(new ShortestPath<>())
					.build();
		}

		return response;
	}

	private void validateGridMapPathfindingRequest(GridMapPathfindingRequest request) {
		if (request == null) {
			throw new PathfindingRequestValidationException("Request cannot be empty");
		}
		Point2d startPoint = request.getStartPoint();
		Point2d endPoint = request.getEndPoint();
		GridMapInfo gridMapInfo = request.getGridMapInfo();
		throwValidationExceptionIfEmptyField(request.getGridMapInfo(), "map_info");
		validateGridMap(request.getGridMapInfo());
		throwValidationExceptionIfEmptyField(startPoint, "start_point");
		validatePoint(startPoint, gridMapInfo, "start_point");
		throwValidationExceptionIfEmptyField(endPoint, "end_point");
		validatePoint(endPoint, gridMapInfo, "start_point");
		gridMapInfo.getWalls().forEach(it -> validatePoint(it, gridMapInfo, "one of the wall"));
		if (gridMapInfo.getWalls().contains(startPoint)) {
			throw new PathfindingRequestValidationException("start_point cannot be wall");
		}
		if (gridMapInfo.getWalls().contains(endPoint)) {
			throw new PathfindingRequestValidationException("start_point cannot be wall");
		}
	}

	public void validateGridMap(GridMapInfo gridMapInfo) {
		if (gridMapInfo.getLeftBorder() == null) {
			throw new PathfindingRequestValidationException("left_border is required for map_info");
		}
		if (gridMapInfo.getRightBorder() == null) {
			throw new PathfindingRequestValidationException("right_border is required for map_info");
		}
		if (gridMapInfo.getTopBorder() == null) {
			throw new PathfindingRequestValidationException("top_border is required for map_info");
		}
		if (gridMapInfo.getBottomBorder() == null) {
			throw new PathfindingRequestValidationException("bottom_border is required for map_info");
		}

		if (gridMapInfo.getLeftBorder() > gridMapInfo.getRightBorder()
				&& gridMapInfo.getBottomBorder() > gridMapInfo.getTopBorder()) {
			throw new PathfindingRequestValidationException("map_info is not valid");
		}

		if (gridMapInfo.getLeftBorder() < -50
				|| gridMapInfo.getLeftBorder() > 50
				|| gridMapInfo.getRightBorder() > 50
				|| gridMapInfo.getRightBorder() < -50
				|| gridMapInfo.getTopBorder() > 50
				|| gridMapInfo.getTopBorder() < -50
				|| gridMapInfo.getBottomBorder() > 50
				|| gridMapInfo.getBottomBorder() < -50) {
			throw new PathfindingRequestValidationException("map bounds are too big");
		}

	}

	public void validatePoint(Point2d point2d, GridMapInfo gridMapInfo, String pointName) {
		if (point2d.getX() == null) {
			throw new PathfindingRequestValidationException(String.format("x field is required in %s", pointName));
		}
		if (point2d.getY() == null) {
			throw new PathfindingRequestValidationException(String.format("y field is required in %s", pointName));
		}
		if (point2d.getX() < gridMapInfo.getLeftBorder() || point2d.getX() > gridMapInfo.getRightBorder()) {
			throw new PathfindingRequestValidationException(String.format("x field of %s is out of the map", pointName));
		}
		if (point2d.getY() < gridMapInfo.getTopBorder() || point2d.getY() > gridMapInfo.getBottomBorder()) {
			throw new PathfindingRequestValidationException(String.format("y field of %s is out of the map", pointName));
		}
	}

	public void throwValidationExceptionIfEmptyField(Object value, String fieldName) {
		if (value == null) {
			throw new PathfindingRequestValidationException(fieldName + " field is required");
		}
	}

}
