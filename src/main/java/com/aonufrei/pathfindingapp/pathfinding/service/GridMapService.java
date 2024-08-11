package com.aonufrei.pathfindingapp.pathfinding.service;

import com.aonufrei.pathfindingapp.dto.GridMapInfo;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class GridMapService implements MapService<Point2d> {

	private final int leftBorder;

	private final int topBorder;

	private final int rightBorder;

	private final int bottomBorder;

	@Setter
	private Set<Point2d> walls = new HashSet<>();


	public GridMapService(GridMapInfo gridMapInfo) {
		this.leftBorder = gridMapInfo.getLeftBorder();
		this.topBorder = gridMapInfo.getTopBorder();
		this.rightBorder = gridMapInfo.getRightBorder();
		this.bottomBorder = gridMapInfo.getBottomBorder();
		this.walls.addAll(gridMapInfo.getWalls());
	}


	@Override
	public List<Point2d> findNeighboursOfVertex(Point2d point) {
		return Stream.of(
				point.copyWithXOffset(1),
				point.copyWithXOffset(-1),
				point.copyWithYOffset(1),
				point.copyWithYOffset(-1),
				point.copyWithOffset(1, 1),
				point.copyWithOffset(1, -1),
				point.copyWithOffset(-1, 1),
				point.copyWithOffset(-1, -1)
		).filter(this::isInMap).filter(p -> !walls.contains(p)).collect(Collectors.toList());
	}

	public boolean isInMap(Point2d point2d) {
		return isBetween(point2d.getX(), leftBorder, rightBorder)
				&& isBetween(point2d.getY(), topBorder, bottomBorder);
	}

	public boolean isBetween(Integer value, Integer minValue, Integer maxValue) {
		return value >= minValue && value < maxValue;
	}

	@Override
	public Double computePrice(Point2d from, Point2d to, Point2d finish) {
		return distanceBetweenVertex(from, to);
	}

	@Override
	public Double distanceBetweenVertex(Point2d p1, Point2d p2) {
		return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
}
