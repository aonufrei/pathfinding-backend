package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.exception.NoPathWasFoundException;
import com.aonufrei.pathfindingapp.pathfinding.enums.Priority;
import com.aonufrei.pathfindingapp.pathfinding.service.MapService;

public interface PathFindingAlgorithm<T> {

	ShortestPath<T> findShortestPath(T start, T finish, Priority priority, MapService<T> mapService) throws NoPathWasFoundException;

}
