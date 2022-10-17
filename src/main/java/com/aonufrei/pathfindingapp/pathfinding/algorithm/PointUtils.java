package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.pathfinding.model.Node;

import java.util.List;

public interface PointUtils<T> {

	List<T> getNeighbours(Node<T> p);

	double computePrice(Node<T> prevNode, T currentPoint, T finishPoint);

}
