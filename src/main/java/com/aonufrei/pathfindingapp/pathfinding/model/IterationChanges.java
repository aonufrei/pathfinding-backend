package com.aonufrei.pathfindingapp.pathfinding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IterationChanges<T> {

	// TODO: Add json property
	private List<T> areProcessing = new LinkedList<>();

	// TODO: Add json property
	private List<T> areAlreadyProcessed = new LinkedList<>();


	public static <T> IterationChanges<T> createProcessingIteration(Collection<T> points) {
		IterationChanges<T> nIterationChanges = new IterationChanges<>();
		nIterationChanges.getAreProcessing().addAll(points);
		return nIterationChanges;
	}

}
