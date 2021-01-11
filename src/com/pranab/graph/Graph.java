package com.pranab.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph<T> {

	private Map<GraphNode<T>,ArrayList<LinkedList<GraphNode<T>>>> graph=new HashMap<>();

	public Map<GraphNode<T>, ArrayList<LinkedList<GraphNode<T>>>> getGraph() {
		return graph;
	}

	public void setGraph(Map<GraphNode<T>, ArrayList<LinkedList<GraphNode<T>>>> graph) {
		this.graph = graph;
	}
	
}
