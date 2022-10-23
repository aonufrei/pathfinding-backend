package com.aonufrei.pathfindingapp.pathfinding.service;

import java.util.List;

public interface MapService<T> {

	List<T> findNeighboursOfVertex(T vertex);

	Double computePrice(T from, T to, T finish);

	Double distanceBetweenVertex(T from, T to);

}
