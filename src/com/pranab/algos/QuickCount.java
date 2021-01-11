package com.pranab.algos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickCount {

	public static void main(String[] args) throws FileNotFoundException {
		// int[] query = { 5, 4, 3, 6, 7, 9, 2, 1, 8 };

		int[] query = new int[10000];
		int i = 0;
		Scanner scanner = new Scanner(new File("input.txt"));
		while (scanner.hasNextInt()) {
			query[i] = scanner.nextInt();
			i++;
		}

		// System.out.println(quickSortWithFirstElem(query, 0, query.length - 1,
		// query.length - 1));

		// System.out.println(quickSortWithLastElem(query, 0, query.length -
		// 1,query.length - 1));

		System.out.println(quickSortWithMedian(query, 0, query.length - 1, query.length - 1));

		scanner.close();
		System.out.println("-------FISH-------");
	}

	/*
	 * public static int quickSortWithFirstElem(int[] query, int startIndex, int
	 * endIndex, int total_com_count) { int pivoitIndex = findPivotIndexFirst(query,
	 * startIndex, endIndex); int left_com_count = 0, right_com_count = 0; if
	 * (pivoitIndex - startIndex > 1) { left_com_count +=
	 * quickSortWithFirstElem(query, startIndex, pivoitIndex - 1, pivoitIndex -
	 * startIndex - 1); } if (endIndex - pivoitIndex > 1) { right_com_count +=
	 * quickSortWithFirstElem(query, pivoitIndex + 1, endIndex, endIndex -
	 * pivoitIndex - 1); } return total_com_count + left_com_count +
	 * right_com_count; }
	 * 
	 * public static int findPivotIndexFirst(int[] query, int startIndex, int
	 * endIndex) { int lowerElementPointer = startIndex + 1, higherElementPointer =
	 * startIndex + 1; while (higherElementPointer <= endIndex) { if
	 * (query[higherElementPointer] <= query[startIndex]) { swap(query,
	 * lowerElementPointer, higherElementPointer); lowerElementPointer++; }
	 * higherElementPointer++; } swap(query, startIndex, --lowerElementPointer);
	 * return lowerElementPointer; }
	 * 
	 * public static int quickSortWithLastElem(int[] query, int startIndex, int
	 * endIndex, int total_com_count) { int pivoitIndex = findPivotIndexLast(query,
	 * startIndex, endIndex); int left_com_count = 0, right_com_count = 0; if
	 * (pivoitIndex - startIndex > 1) { left_com_count +=
	 * quickSortWithLastElem(query, startIndex, pivoitIndex - 1, pivoitIndex -
	 * startIndex - 1); } if (endIndex - pivoitIndex > 1) { right_com_count +=
	 * quickSortWithLastElem(query, pivoitIndex + 1, endIndex, endIndex -
	 * pivoitIndex - 1); } return total_com_count + left_com_count +
	 * right_com_count; }
	 * 
	 * public static int findPivotIndexLast(int[] query, int startIndex, int
	 * endIndex) { swap(query, startIndex, endIndex); int lowerElementPointer =
	 * startIndex + 1, higherElementPointer = startIndex + 1; while
	 * (higherElementPointer <= endIndex) { if (query[higherElementPointer] <=
	 * query[startIndex]) { swap(query, lowerElementPointer, higherElementPointer);
	 * lowerElementPointer++; } higherElementPointer++; } swap(query, startIndex,
	 * --lowerElementPointer); return lowerElementPointer; }
	 */
	/*
	 * public static int findPivotIndexLast(int[] query, int startIndex, int
	 * endIndex) { int lowerElementPointer = startIndex, higherElementPointer =
	 * startIndex; while (higherElementPointer < endIndex) { if
	 * (query[higherElementPointer] <= query[endIndex]) { swap(query,
	 * lowerElementPointer, higherElementPointer); lowerElementPointer++; }
	 * higherElementPointer++; } swap(query, endIndex, lowerElementPointer); return
	 * lowerElementPointer; }
	 */

	public static int quickSortWithMedian(int[] query, int startIndex, int endIndex, int total_com_count) {
		int pivoitIndex = findPivotIndexMedian(query, startIndex, endIndex);
		int left_com_count = 0, right_com_count = 0;
		if (pivoitIndex - startIndex > 1) {
			left_com_count += quickSortWithMedian(query, startIndex, pivoitIndex - 1, pivoitIndex - startIndex - 1);
		}
		if (endIndex - pivoitIndex > 1) {
			right_com_count += quickSortWithMedian(query, pivoitIndex + 1, endIndex, endIndex - pivoitIndex - 1);
		}
		return total_com_count + left_com_count + right_com_count;
	}

	public static int findPivotIndexMedian(int[] query, int startIndex, int endIndex) {
		int medianIndex = (startIndex + endIndex) / 2;
		int pivot = startIndex;
		if ((query[medianIndex] > query[startIndex] && query[medianIndex] < query[endIndex])
				|| (query[medianIndex] < query[startIndex] && query[medianIndex] > query[endIndex])) {
			pivot = medianIndex;
		} else if ((query[endIndex] > query[startIndex] && query[endIndex] < query[medianIndex])
				|| (query[endIndex] < query[startIndex] && query[endIndex] > query[medianIndex])) {
			pivot = endIndex;
		}
		swap(query, startIndex, pivot);
		int lowerElementPointer = startIndex + 1, higherElementPointer = startIndex + 1;
		while (higherElementPointer <= endIndex) {
			if (query[higherElementPointer] <= query[startIndex]) {
				swap(query, lowerElementPointer, higherElementPointer);
				lowerElementPointer++;
			}
			higherElementPointer++;
		}
		swap(query, startIndex, --lowerElementPointer);
		return lowerElementPointer;
	}

	private static void swap(int[] query, int lowerElementPointer, int higherElementPointer) {
		int temp = query[higherElementPointer];
		query[higherElementPointer] = query[lowerElementPointer];
		query[lowerElementPointer] = temp;
	}
}
