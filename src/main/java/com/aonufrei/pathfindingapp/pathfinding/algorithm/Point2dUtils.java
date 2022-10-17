package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.pathfinding.model.Node;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Point2dUtils implements PointUtils<Point2d> {

	private static final int MAP_LEFT_BORDER = -3;
	private static final int MAP_RIGHT_BORDER = 8;
	private static final int MAP_UP_BORDER = 4;
	private static final int MAP_BOTTOM_BORDER = -2;

	@Override
	public List<Point2d> getNeighbours(Node<Point2d> p) {
		final Point2d current = p.getVertex();
		return Stream.of(
				new Point2d(current.getX() - 1, current.getY()),
				new Point2d(current.getX() + 1, current.getY()),
				new Point2d(current.getX(), current.getY() - 1),
				new Point2d(current.getX(), current.getY() + 1),
				new Point2d(current.getX() - 1, current.getY() - 1),
				new Point2d(current.getX() - 1, current.getY() + 1),
				new Point2d(current.getX() + 1, current.getY() - 1),
				new Point2d(current.getX() + 1, current.getY() + 1)
		).filter(this::isInWorld)
				.filter(n -> !collidesObstacles(n)).collect(Collectors.toList());
	}

	@Override
	public double computePrice(Node<Point2d> prevNode, Point2d currentPoint, Point2d finishPoint) {
//		return prevNode.getPrice() + distanceBetween(currentPoint, prevNode.getVertex())
//				+ distanceBetween(currentPoint, finishPoint);
		return distanceBetween(currentPoint, finishPoint);
	}

	public double distanceBetween(Point2d p1, Point2d p2) {
		return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}

	public boolean collidesObstacles(Point2d point2d) {
		int pointX = point2d.getX();
		int pointY = point2d.getY();
		return isInBoundary(pointX, pointY, -2, 2, 3, 2)
				|| isInBoundary(pointX, pointY, 2, 3, 3, -1)
				|| isInBoundary(pointX, pointY, 1, 3, -1, -2)
				|| isInBoundary(pointX, pointY, 5, 6, 4, 1)
				|| isInBoundary(pointX, pointY, 5, 7, 2, 1);
	}

	public boolean isInBoundary(int x, int y, int leftX, int rightX, int topY, int bottomY) {
		return x >= leftX && x <= rightX && y >= bottomY && y <= topY;
	}

	private boolean isInWorld(Point2d p) {
		return isInBoundary(p.getX(), p.getY(), MAP_LEFT_BORDER, MAP_RIGHT_BORDER, MAP_UP_BORDER, MAP_BOTTOM_BORDER);
	}
}
