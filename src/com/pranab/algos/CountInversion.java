package com.pranab.algos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountInversion {

	public static void main(String[] args) throws FileNotFoundException {
		//int[] test = new int[] { 5,4,3,1,2,9,8,7,6 };
		
		  int[] test=new int[100000]; int i=0; Scanner scanner = new Scanner(new
		  File("input.txt")); while (scanner.hasNextInt()) { test[i]=scanner.nextInt();
		  i++; }
		 
		System.out.println(recurseMethod(test, 0, test.length / 2, test.length - 1,0));	
		scanner.close();
		System.out.println("----FINISHED-----");
	}

	public static long recurseMethod(int[] arr, int start, int mid, int end,long count_Inversions) {
		int lowerLength = mid - start + 1, upperLength = end - mid;
		long left_inversion=0,right_Inversion=0;
		if (lowerLength > 1) {
			if (start + 1 != mid) {
				left_inversion=recurseMethod(arr, start, start + lowerLength / 2, mid,count_Inversions);
			} else {
				left_inversion=recurseMethod(arr, start, start, mid,count_Inversions);
			}
		}
		if (upperLength > 1) {
			right_Inversion=recurseMethod(arr, mid + 1, mid + 1 + upperLength / 2, end,count_Inversions);
		}
		return count_Inversions=merge(arr, start, mid, mid + 1, end,left_inversion+right_Inversion);
	}

	public static long merge(int[] arr, int start, int lowermid, int uppermid, int end,long count_Inversions) {
		int[] leftArr = new int[lowermid - start + 1];
		for (int i = start, j = 0; i <= lowermid; i++, j++) {
			leftArr[j] = arr[i];
		}
		int[] rightArr = new int[end - uppermid + 1];
		for (int i = uppermid, j = 0; i <= end; i++, j++) {
			rightArr[j] = arr[i];
		}
		int i = start, j = 0, k = 0;
		while (i <= end) {
			if (j == leftArr.length) {
				//count_Inversions+=leftArr.length-j;
				while (i <= end) {
					arr[i] = rightArr[k];
					k++;
					i++;
				}
				break;
			} else if (k == rightArr.length) {
				while (i <= end) {
					arr[i] = leftArr[j];
					j++;
					i++;
				}
				break;
			}
			if (leftArr[j] <= rightArr[k]) {
				arr[i] = leftArr[j];
				j++;
				i++;
			} else {
				arr[i] = rightArr[k];
				count_Inversions+=leftArr.length-j;
				k++;
				i++;
			}
		}
		return count_Inversions;
	}
}
