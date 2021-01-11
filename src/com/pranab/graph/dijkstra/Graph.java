package com.pranab.graph.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph<T> {

	private Map<T,GraphNode<T>> nodes;
	
	private Map<GraphNode<T>,LinkedList<Edge<T>>> structure;	

	public Graph() {
		super();
		this.nodes = new HashMap<>();
		this.structure = new HashMap<>();
	}

	public Map<T,GraphNode<T>> getNodes() {
		return nodes;
	}

	public Map<GraphNode<T>, LinkedList<Edge<T>>> getStructure() {
		return structure;
	}
}
