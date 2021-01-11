package com.pranab.graph.dijkstra.heap;

public class HeapNode<T> {

	private int distance;
	private GraphNode<T> node;

	public HeapNode() {
		super();
	}

	public HeapNode(int distance, GraphNode<T> node) {
		super();
		this.distance = distance;
		this.node = node;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
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
		result = prime * result + distance;
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
		HeapNode other = (HeapNode) obj;
		if (distance != other.distance)
			return false;
		return true;
	}

}
