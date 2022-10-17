package com.aonufrei.pathfindingapp.pathfinding.algorithm;

import com.aonufrei.pathfindingapp.pathfinding.model.IterationChanges;
import com.aonufrei.pathfindingapp.pathfinding.model.Node;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;

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

	private final LinkedList<IterationChanges<T>> iterationChanges = new LinkedList<>();

	@Override
	public List<T> findShortestPath(T start, T finish, PointUtils<T> pointUtils) {
		iterationChanges.clear();
		Node<T> startNode = new Node<>(start, 0, null);
		Queue<Node<T>> q = new PriorityQueue<>(Comparator.comparing(Node::getPrice));
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
			List<Node<T>> neighbours = pointUtils.getNeighbours(currentNode).stream()
					.filter(n -> q.stream().noneMatch(queueNode -> queueNode.getVertex().equals(n)))
					.filter(n -> !visited.contains(n))
					.map(it -> new Node<>(it, pointUtils.computePrice(currentNode, it, finish), currentNode))
					.collect(Collectors.toList());
			iteration.getAreProcessing().addAll(neighbours.stream().map(Node::getVertex).collect(Collectors.toList()));
			for (Node<T> neighbour: neighbours) {
				if (neighbour.getVertex().equals(finish)) {
					LinkedList<T> route = traverseToRoot(neighbour);
					iterationChanges.add(IterationChanges.createRouteIteration(route));
					return route;
				}
			}
			iterationChanges.add(iteration);
			q.addAll(neighbours);
		}
		return new LinkedList<>();
	}

	public List<IterationChanges<T>> getLastIterationChanges() {
		return iterationChanges;
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
		PathFindingAlgorithm<Point2d> algorithm = new AStarAlgorithm<>();
		List<Point2d> shortestPath = algorithm.findShortestPath(new Point2d(0, 0), new Point2d(7, 3), new Point2dUtils());
		shortestPath.forEach(p -> System.out.printf("%s; ", p));
		System.out.println();
	}

}
