package com.aonufrei.pathfindingapp.service.rest;

import com.aonufrei.pathfindingapp.dto.GridMapInfo;
import com.aonufrei.pathfindingapp.exception.PathfindingRequestValidationException;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PathfindingRestServiceTest {

	@Autowired
	private PathfindingRestService pathfindingRestService;

	@Test
	void testValidateGridMap() {
		GridMapInfo gridMapInfo = new GridMapInfo();
		gridMapInfo.setTopBorder(-1);
		gridMapInfo.setBottomBorder(10);
		gridMapInfo.setLeftBorder(3);
		gridMapInfo.setRightBorder(10);

		assertDoesNotThrow(() -> pathfindingRestService.validateGridMap(gridMapInfo));
		gridMapInfo.setLeftBorder(null);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "left_border is required for map_info");
		gridMapInfo.setLeftBorder(1);
		gridMapInfo.setRightBorder(null);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "right_border is required for map_info");
		gridMapInfo.setRightBorder(1);
		gridMapInfo.setTopBorder(null);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "top_border is required for map_info");
		gridMapInfo.setTopBorder(1);
		gridMapInfo.setBottomBorder(null);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "bottom_border is required for map_info");
		gridMapInfo.setBottomBorder(1);

		gridMapInfo.setTopBorder(100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setTopBorder(-100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setTopBorder(0);
		gridMapInfo.setRightBorder(100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setRightBorder(-100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setRightBorder(10);
		gridMapInfo.setLeftBorder(-100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setLeftBorder(100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setLeftBorder(-10);
		gridMapInfo.setBottomBorder(-100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setBottomBorder(100);
		assertThrowsExactly(PathfindingRequestValidationException.class, () -> pathfindingRestService.validateGridMap(gridMapInfo), "map bounds are too big");
		gridMapInfo.setBottomBorder(30);
	}

	@Test
	void testValidatePoint() {
		GridMapInfo gridMapInfo = new GridMapInfo(0, 10, 0, 10, Collections.emptyList());
		assertDoesNotThrow(() -> pathfindingRestService.validatePoint(new Point2d(0, 0), gridMapInfo, " some point"));
		assertThrowsExactly(PathfindingRequestValidationException.class,
				() -> pathfindingRestService.validatePoint(new Point2d(-1, 1), gridMapInfo, "start_point"),
				"x field of start_point is out of the map");
		assertThrowsExactly(PathfindingRequestValidationException.class,
				() -> pathfindingRestService.validatePoint(new Point2d(100, 1), gridMapInfo, "start_point"),
				"x field of start_point is out of the map");
		assertThrowsExactly(PathfindingRequestValidationException.class,
				() -> pathfindingRestService.validatePoint(new Point2d(1, -50), gridMapInfo, "start_point"),
				"y field of start_point is out of the map");
		assertThrowsExactly(PathfindingRequestValidationException.class,
				() -> pathfindingRestService.validatePoint(new Point2d(1, 100), gridMapInfo, "start_point"),
				"y field of start_point is out of the map");
	}

}