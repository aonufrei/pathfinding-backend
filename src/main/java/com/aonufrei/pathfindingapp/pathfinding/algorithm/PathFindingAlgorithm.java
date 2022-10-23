package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.exception.NoPathWasFoundException;
import com.aonufrei.pathfindingapp.pathfinding.service.MapService;

public interface PathFindingAlgorithm<T> {

	ShortestPath<T> findShortestPath(T start, T finish, MapService<T> mapService) throws NoPathWasFoundException;

}
