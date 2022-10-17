package com.aonufrei.pathfindingapp.pathfinding.model;

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

	private List<T> areProcessing = new LinkedList<>();

	private List<T> areAlreadyProcessed = new LinkedList<>();

	private List<T> route = new LinkedList<>();

	public static <T> IterationChanges<T> createProcessingIteration(Collection<T> points) {
		IterationChanges<T> nIterationChanges = new IterationChanges<>();
		nIterationChanges.getAreProcessing().addAll(points);
		return nIterationChanges;
	}

	public static <T> IterationChanges<T> createProcessingAndProcessedIteration(Collection<T> processing, Collection<T> processed) {
		IterationChanges<T> nIterationChanges = new IterationChanges<>();
		nIterationChanges.getAreProcessing().addAll(processing);
		nIterationChanges.getAreAlreadyProcessed().addAll(processed);
		return nIterationChanges;
	}

	public static <T> IterationChanges<T> createRouteIteration(Collection<T> route) {
		IterationChanges<T> nIterationChanges = new IterationChanges<>();
		nIterationChanges.getRoute().addAll(route);
		return nIterationChanges;
	}
}
