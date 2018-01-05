package com.tzyl.hackerrank.algorithms.sorting.quicksort2;

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
            int q = stablePartition(A, p, r);
            quicksort(A, p, q - 1);
            quicksort(A, q + 1, r);
            printArray(A, p, r);
        }
    }

    public static int stablePartition(int[] A, int p, int r) {
        int pivot = A[p];
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int i = p + 1; i <= r; i++) {
            if (A[i] < pivot) {
                left.add(A[i]);
            } else {
                right.add(A[i]);
            }
        }
        copy(left, A, p);
        int q = p + left.size();
        A[q] = pivot;
        copy(right, A, q + 1);
        return q;
    }

    public static void copy(ArrayList<Integer> list, int[] A, int i) {
        for (int x : list) {
            A[i++] = x;
        }
    }

    public static void printArray(int[] A, int p, int r) {
        for (int i = p; i <= r; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

}
