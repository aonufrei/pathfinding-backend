package com.aonufrei.pathfindingapp.pathfinding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Node<T> {

	private T vertex;

	private double price = 0;

	private double distanceToFinish = Double.POSITIVE_INFINITY;

	private Node<T> parent;

}
