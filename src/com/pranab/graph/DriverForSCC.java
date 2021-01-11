package com.pranab.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DriverForSCC {

	public static void main(String[] args) {
		Graph<Integer> struc = new Graph<>();
		extractData(struc);
		GraphNode<Integer>[] orderedNodes = inverseDepthFirstSearch(struc);
		resetNodesVisibility(orderedNodes);
		List<Integer> connectedComponetSize=findStronglyConnectedGraph(struc, orderedNodes);
		sortAndPrint(connectedComponetSize);
		System.out.println("-----Found All Connectivity------");
	}

	private static void sortAndPrint(List<Integer> connectedComponetSize) {
		Collections.sort(connectedComponetSize);
		for(int i:connectedComponetSize) {
			System.out.println(i);
		}// TODO Auto-generated method stub
		
	}

	private static List<Integer> findStronglyConnectedGraph(Graph<Integer> struc, GraphNode<Integer>[] orderedNodes) {
		List<Integer> connectedComponetSize=new ArrayList<>();
		Map<GraphNode<Integer>, ArrayList<LinkedList<GraphNode<Integer>>>> nodesMap = struc.getGraph();
		for (int index = orderedNodes.length - 1; index >= 0; index--) {
			if (!orderedNodes[index].isVisited()) {
				connectedComponetSize.add(depthFirstSearch(nodesMap, orderedNodes[index], 0,0));
			}
		}
		return connectedComponetSize;
	}

	private static int depthFirstSearch(Map<GraphNode<Integer>, ArrayList<LinkedList<GraphNode<Integer>>>> nodesMap,
			GraphNode<Integer> key, int index,int count) {
		LinkedList<GraphNode<Integer>> linkedNodes = nodesMap.get(key).get(index);
		key.setVisited(true);
		for (GraphNode<Integer> node : linkedNodes) {
			if (!node.isVisited()) {
				count=depthFirstSearch(nodesMap, node, index,count);
			}
		}
		count++;
		return count;
	}

	private static void resetNodesVisibility(GraphNode<Integer>[] orderedNodes) {
		for (GraphNode<Integer> node : orderedNodes) {
			if (node != null) {
				node.setVisited(false);
			}
		}
	}

	public static void extractData(Graph<Integer> struc) {
		GraphNode<Integer>[] allNodesArray = new GraphNode[875715];
		Map<GraphNode<Integer>, ArrayList<LinkedList<GraphNode<Integer>>>> graph = struc.getGraph();
		try (Scanner scanner = new Scanner(new File("data.txt"))) {
			int tail = 0;
			int head = 0;
			while (scanner.hasNextLine()) {
				tail = scanner.nextInt();
				if (allNodesArray[tail] == null) {
					allNodesArray[tail] = new GraphNode<>(tail, false);
				}
				head = scanner.nextInt();
				if (allNodesArray[head] == null) {
					allNodesArray[head] = new GraphNode<>(head, false);
				}
				if (graph.containsKey(allNodesArray[tail])) {
					graph.get(allNodesArray[tail]).get(0).add(allNodesArray[head]);
				} else {
					LinkedList<GraphNode<Integer>> outGoingList = new LinkedList<>();
					outGoingList.add(allNodesArray[head]);
					LinkedList<GraphNode<Integer>> inComingList = new LinkedList<>();
					ArrayList<LinkedList<GraphNode<Integer>>> arrayList = new ArrayList<>();
					arrayList.add(0, outGoingList);
					arrayList.add(1, inComingList);
					graph.put(allNodesArray[tail], arrayList);
				}
				if (graph.containsKey(allNodesArray[head])) {
					graph.get(allNodesArray[head]).get(1).add(allNodesArray[tail]);
				} else {
					LinkedList<GraphNode<Integer>> outGoingList = new LinkedList<>();
					LinkedList<GraphNode<Integer>> inComingList = new LinkedList<>();
					inComingList.add(allNodesArray[tail]);
					ArrayList<LinkedList<GraphNode<Integer>>> arrayList = new ArrayList<>();
					arrayList.add(0, outGoingList);
					arrayList.add(1, inComingList);
					graph.put(allNodesArray[head], arrayList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GraphNode<Integer>[] inverseDepthFirstSearch(Graph<Integer> struc) {
		int order = 0;
		GraphNode<Integer>[] orderedNodes = new GraphNode[875714];
		Map<GraphNode<Integer>, ArrayList<LinkedList<GraphNode<Integer>>>> nodesMap = struc.getGraph();
		for (GraphNode<Integer> nodeKey : nodesMap.keySet()) {
			if (!nodeKey.isVisited()) {
				order = depthFirstSearch(nodesMap, nodeKey, 1, order, orderedNodes);
			}
		}
		return orderedNodes;
	}

	public static int depthFirstSearch(Map<GraphNode<Integer>, ArrayList<LinkedList<GraphNode<Integer>>>> nodesMap,
			GraphNode<Integer> key, int index, int order, GraphNode<Integer>[] orderedNodes) {
		LinkedList<GraphNode<Integer>> linkedNodes = nodesMap.get(key).get(index);
		key.setVisited(true);
		for (GraphNode<Integer> node : linkedNodes) {
			if (!node.isVisited()) {
				order = depthFirstSearch(nodesMap, node, index, order, orderedNodes);
			}
		}
		orderedNodes[order] = key;
		order++;
		return order;
	}
}
