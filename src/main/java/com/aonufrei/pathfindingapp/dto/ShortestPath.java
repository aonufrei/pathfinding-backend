package com.aonufrei.pathfindingapp.dto;

import com.aonufrei.pathfindingapp.pathfinding.model.IterationChanges;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortestPath<T> {

	private List<IterationChanges<T>> iterations = new ArrayList<>();

	private List<T> route;

}
