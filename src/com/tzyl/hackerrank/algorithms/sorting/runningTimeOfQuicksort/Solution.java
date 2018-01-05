package com.tzyl.hackerrank.algorithms.sorting.runningTimeOfQuicksort;

import java.util.*;

public class Solution {
    public static int insertionShifts = 0;
    public static int quicksortSwaps = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
            B[i] = A[i];
        }
        quicksort(A);
        insertionSort(B);
        System.out.println(insertionShifts - quicksortSwaps);
    }

    public static void quicksort(int[] A) {
        quicksort(A, 0, A.length - 1);
    }

    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
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
                quicksortSwaps++;
            }
        }
        // Swap pivot into correct position.
        swap(A, r, i + 1);
        quicksortSwaps++;
        return i + 1;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void insertionSort(int[] ar) {
        for (int i = 1; i < ar.length; i++) {
            int j =  i;
            int temp = ar[j];
            while (j > 0 && ar[j - 1] > temp) {
                ar[j] = ar[j - 1];
                insertionShifts++;
                j--;
            }
            ar[j] = temp;
        }
    }
}
