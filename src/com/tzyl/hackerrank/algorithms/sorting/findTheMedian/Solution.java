package com.tzyl.hackerrank.algorithms.sorting.findTheMedian;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        System.out.println(findMedian(A));
    }

    public static int findMedian(int[] A) {
        return findMedian(A, 0, A.length - 1);
    }

    public static int findMedian(int[] A, int p, int r) {
        if (p > r) throw new IllegalArgumentException();
        int q = partition(A, p, r);
        if (2 * q == A.length - 1) {
            return A[q];
        } else if (2 * q < A.length - 1) {
            return findMedian(A, q + 1, r);
        } else {
            return findMedian(A, p, q -1 );
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

}
