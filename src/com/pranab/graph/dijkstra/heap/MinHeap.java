package com.pranab.graph.dijkstra.heap;

public class MinHeap<T> {

	private Object[] heap;
	private int size;

	public MinHeap(int size) {
		super();
		this.size = size;
		this.heap = new Object[size];
	}

	public int parent(int index) {
		return (index - 1) / 2;
	}

	public int leftChild(int index) {
		return index * 2 + 1;
	}

	public int rightChild(int index) {
		return index * 2 + 2;
	}

	public void minify(int index) {
		int smallestValueIndex = index;
		int smallestValue = ((HeapNode) heap[index]).getDistance();
		int leftChildIndex = leftChild(index);
		if (leftChildIndex < size && ((HeapNode) heap[leftChildIndex]).getDistance() < smallestValue) {
			smallestValueIndex = leftChildIndex;
			smallestValue = ((HeapNode) heap[leftChildIndex]).getDistance();
		}
		int rightChildIndex = rightChild(index);
		if (rightChildIndex < size && ((HeapNode) heap[rightChildIndex]).getDistance() < smallestValue) {
			smallestValueIndex = rightChildIndex;
		}
		if (smallestValueIndex != index) {
			swapNodes(index, smallestValueIndex);
			minify(smallestValueIndex);
		}
	}

	private void swapNodes(int nodeOneIndex, int nodeTwoIndex) {
		Object temp = heap[nodeTwoIndex];
		heap[nodeTwoIndex] = heap[nodeOneIndex];
		heap[nodeOneIndex] = temp;
	}

	public void buildMinify() {
		for(int index=size/2;index>=0;index--) {
			minify(index);
		}
	}

	public HeapNode<T> extractMin() {
		if(size==0) {
			return null;
		}
		Object extractedData=heap[0];
		heap[0]=heap[size-1];
		size--;
		minify(0);
		return (HeapNode<T>)extractedData;
	}
	
	public boolean modifyKey(int index,int reducedKey) {
		HeapNode existingNode=((HeapNode<T>)heap[index]);
		if(existingNode.getDistance()<reducedKey) {
			return false;
		}
		existingNode.setDistance(reducedKey);
		while(index>0 && ((HeapNode<T>)heap[parent(index)]).getDistance()>((HeapNode<T>)heap[index]).getDistance()) {
			swapNodes(index,parent(index));
			index=parent(index);
		}
		return true;
	}
}
