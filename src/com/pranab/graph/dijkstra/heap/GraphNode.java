package com.pranab.graph.dijkstra.heap;

import java.util.LinkedList;
import java.util.List;

public class GraphNode<T> {

	private T data;
	private boolean visited;
	private double distance;
	private List<GraphNode<T>> path;

	public GraphNode() {
		super();
	}

	public GraphNode(T data) {
		super();
		this.data = data;
		this.visited = false;
		this.distance = Double.POSITIVE_INFINITY;
		this.path = new LinkedList<>();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<GraphNode<T>> getPath() {
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		GraphNode other = (GraphNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	
}
