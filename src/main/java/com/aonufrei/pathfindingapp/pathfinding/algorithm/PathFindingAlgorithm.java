package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import java.util.List;

public interface PathFindingAlgorithm<T> {

	List<T> findShortestPath(T start, T finish, PointUtils<T> pointUtils);

}
