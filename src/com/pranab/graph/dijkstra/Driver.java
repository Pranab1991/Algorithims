package com.pranab.graph.dijkstra;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Driver {

	private static Comparator<GraphNode<Character>> comparator = (o1, o2) -> (int) o1.getDistance()
			- (int) o2.getDistance();

	public static void main(String[] args) {
		Graph graph = extractAndLoadData();
		processDistanceFromSource(graph, (GraphNode<Character>) graph.getNodes().get('B'));
		printDistanceAndPathFromSource(graph);
		System.out.println("-----------WIN BY KO------------");
	}

	private static void printDistanceAndPathFromSource(Graph<Character> graph) {
		for (Map.Entry<Character, GraphNode<Character>> node : graph.getNodes().entrySet()) {
			System.out.print(node.getKey() + " : Distance from source = " + node.getValue().getDistance()
					+ " ,Path from source = ");
			for (GraphNode<Character> pathNode : node.getValue().getPath()) {
				System.out.print(pathNode.getData() + ",");
			}
			System.out.println();
		}

	}

	private static void processDistanceFromSource(Graph<Character> graph, GraphNode<Character> source) {
		Queue<GraphNode<Character>> queue = new LinkedList<>();
		Map<Character, GraphNode<Character>> nodes = graph.getNodes();
		Map<GraphNode<Character>, LinkedList<Edge<Character>>> structure = graph.getStructure();
		source.setDistance(0);
		source.setVisited(true);
		source.getPath().add(source);
		queue.offer(source);
		while (!queue.isEmpty()) {
			GraphNode<Character> currentNode = queue.poll();
			LinkedList<Edge<Character>> edges = structure.get(currentNode);
			GraphNode<Character> minNode = null;
			for (Edge<Character> edge : edges) {
				if (!edge.getNode().isVisited()) {
					GraphNode<Character> recheableNode = edge.getNode();
					double currentdistance=recheableNode.getDistance();
					double newdistance=currentNode.getDistance() + edge.getWeight();
					if(newdistance<currentdistance) {
						recheableNode.getPath().clear();
						recheableNode.getPath().addAll(currentNode.getPath());
						recheableNode.getPath().add(recheableNode);
						recheableNode.setDistance(newdistance);
					}
					if (minNode == null || comparator.compare(minNode, recheableNode) > 0) {
						minNode = recheableNode;
					}
				}
			}
			if (minNode != null) {
				minNode.setVisited(true);				
				queue.offer(minNode);
			}
		}

	}

	private static Graph<Character> extractAndLoadData() {
		Graph<Character> graph = new Graph<>();
		Map<Character, GraphNode<Character>> nodes = graph.getNodes();
		Map<GraphNode<Character>, LinkedList<Edge<Character>>> structure = graph.getStructure();
		try (Scanner scanner = new Scanner(new File("dijkstraInput.txt"))) {
			char tail = 0;
			char head = 0;
			int weight = 0;
			while (scanner.hasNextLine()) {
				tail = scanner.next().charAt(0);
				if (!nodes.containsKey(tail)) {
					nodes.put(tail, new GraphNode<>(tail));
				}
				head = scanner.next().charAt(0);
				if (!nodes.containsKey(head)) {
					nodes.put(head, new GraphNode<>(head));
				}
				weight = scanner.nextInt();
				if (structure.containsKey(nodes.get(tail))) {
					structure.get(nodes.get(tail)).add(new Edge<>(weight, nodes.get(head)));
				} else {
					LinkedList<Edge<Character>> edge = new LinkedList<>();
					edge.add(new Edge<>(weight, nodes.get(head)));
					structure.put(nodes.get(tail), edge);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}
}
