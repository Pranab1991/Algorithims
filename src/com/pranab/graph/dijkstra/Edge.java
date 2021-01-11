package com.pranab.graph.dijkstra;

import java.util.Comparator;

public class Edge<T>{

	private int weight;
	private GraphNode<T> node;

	public Edge(int weight, GraphNode<T> node) {
		super();
		this.weight = weight;
		this.node = node;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public GraphNode<T> getNode() {
		return node;
	}

	public void setNode(GraphNode<T> node) {
		this.node = node;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

}
