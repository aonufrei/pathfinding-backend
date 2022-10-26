package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.dto.GridMapInfo;
import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.exception.NoPathWasFoundException;
import com.aonufrei.pathfindingapp.pathfinding.enums.Priority;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.aonufrei.pathfindingapp.pathfinding.service.GridMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GridMapPathfindingAlgorithmTest {

	@Autowired
	GridMapPathfindingAlgorithm algorithm;

	@Test
	public void testStraightLinePathTest() throws NoPathWasFoundException {

		GridMapInfo gridMapInfo = new GridMapInfo();
		gridMapInfo.setLeftBorder(-10);
		gridMapInfo.setRightBorder(10);
		gridMapInfo.setTopBorder(-10);
		gridMapInfo.setBottomBorder(10);

		ShortestPath<Point2d> shortestPath = algorithm.findShortestPath(new Point2d(1, 1), new Point2d(1, 5), Priority.QUALITY, new GridMapService(gridMapInfo));
		assertEquals(5, shortestPath.getRoute().size(), "Route length is not valid");

		ShortestPath<Point2d> shortestPath2 = algorithm.findShortestPath(new Point2d(1, 1), new Point2d(5, 1), Priority.QUALITY, new GridMapService(gridMapInfo));
		assertEquals(5, shortestPath2.getRoute().size(), "Route length is not valid");

		ShortestPath<Point2d> shortestPath3 = algorithm.findShortestPath(new Point2d(1, 1), new Point2d(1, -4), Priority.QUALITY, new GridMapService(gridMapInfo));
		assertEquals(6, shortestPath3.getRoute().size(), "Route length is not valid");

		ShortestPath<Point2d> shortestPath4 = algorithm.findShortestPath(new Point2d(1, 1), new Point2d(-4, 1), Priority.QUALITY, new GridMapService(gridMapInfo));
		assertEquals(6, shortestPath4.getRoute().size(), "Route length is not valid");
	}
}