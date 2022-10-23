package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.dto.ShortestPath;
import com.aonufrei.pathfindingapp.exception.NoPathWasFoundException;
import com.aonufrei.pathfindingapp.pathfinding.model.IterationChanges;
import com.aonufrei.pathfindingapp.pathfinding.model.Node;
import com.aonufrei.pathfindingapp.pathfinding.service.MapService;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class AStarAlgorithm<T> implements PathFindingAlgorithm<T> {

	@Override
	public ShortestPath<T> findShortestPath(T start, T finish, MapService<T> mapService) throws NoPathWasFoundException {
		ShortestPath<T> shortestPath = new ShortestPath<>();
		LinkedList<IterationChanges<T>> iterationChanges = new LinkedList<>();
		Node<T> startNode = new Node<>(start, 0, mapService.distanceBetweenVertex(start, finish), null);
		Queue<Node<T>> q = new PriorityQueue<>(Comparator.comparingDouble(node -> node.getPrice()));//Comparator.comparing(Node::getPrice));
		q.add(startNode);
		iterationChanges.add(IterationChanges.createProcessingIteration(Collections.singletonList(start)));
		Set<T> visited = new HashSet<>();
		while (!q.isEmpty()) {
			IterationChanges<T> iteration = new IterationChanges<>();
			Node<T> currentNode = q.poll();
			if (currentNode != null) {
				iteration.getAreAlreadyProcessed().add(currentNode.getVertex());
				visited.add(currentNode.getVertex());
			}
			T currentVertex = currentNode.getVertex();
			List<Node<T>> neighbours = mapService.findNeighboursOfVertex(currentVertex).stream()
					.filter(v -> !visited.contains(v))
					.filter(v -> q.stream().noneMatch(qn -> qn.getVertex().equals(v)))
					.map(v -> new Node<>(v, currentNode.getPrice() + mapService.computePrice(currentVertex, v, finish), mapService.distanceBetweenVertex(v, finish), currentNode))
					.collect(Collectors.toList());
			iteration.getAreProcessing().addAll(neighbours.stream().map(Node::getVertex).collect(Collectors.toList()));
			for (Node<T> neighbour: neighbours) {
				if (neighbour.getVertex().equals(finish)) {
					LinkedList<T> route = traverseToRoot(neighbour);
					shortestPath.setIterations(iterationChanges);
					shortestPath.setRoute(route);
					return shortestPath;
				}
			}
			iterationChanges.add(iteration);
			q.addAll(neighbours);
		}
		throw new NoPathWasFoundException("No path was found");
	}

	private LinkedList<T> traverseToRoot(Node<T> node) {
		LinkedList<T> points = new LinkedList<>();
		points.addFirst(node.getVertex());
		while (node.getParent() != null) {
			node = node.getParent();
			points.addFirst(node.getVertex());
		}
		return points;
	}

	public static void main(String[] args) {
//		PathFindingAlgorithm<Point2d> algorithm = new AStarAlgorithm<>();
//		List<Point2d> shortestPath = algorithm.findShortestPath(new Point2d(0, 0), new Point2d(7, 3), new Point2dUtils());
//		shortestPath.forEach(p -> System.out.printf("%s; ", p));
//		System.out.println();
	}

}
