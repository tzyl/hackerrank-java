package com.tzyl.hackerrank.algorithms.sorting.quicksort3;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        quicksort(A);
    }

    public static void quicksort(int[] A) {
        quicksort(A, 0, A.length - 1);
    }

    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            printArray(A, 0, A.length - 1);
            quicksort(A, p, q - 1);
            quicksort(A, q + 1, r);
        }
    }

    public static int partition(int[] A, int p, int r) {
        int pivot = A[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (A[j] < pivot) {
                i++;
                swap(A, i, j);
            }
        }
        // Swap pivot into correct position.
        swap(A, r, i + 1);
        return i + 1;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void printArray(int[] A, int p, int r) {
        for (int i = p; i <= r; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

}
