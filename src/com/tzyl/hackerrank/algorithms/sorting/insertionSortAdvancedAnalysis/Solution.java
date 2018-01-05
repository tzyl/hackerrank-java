package com.tzyl.hackerrank.algorithms.sorting.insertionSortAdvancedAnalysis;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            // Merge sort to count inversions.
            System.out.println(countInversions(A));
        }
    }

    public static long countInversions(int[] A) {
        return countInversions(A, 0, A.length - 1);
    }

    public static long countInversions(int[] A, int p, int r) {
        if (p >= r) {
            return 0;
        }
        long inversions = 0;
        int q = (p + r) / 2;
        inversions += countInversions(A, p, q);
        inversions += countInversions(A, q + 1, r);
        inversions += merge(A, p, q, r);
        return inversions;
    }

    public static long merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + 1 + j];
        }
        int i = 0;
        int j = 0;
        long inversions = 0;
        for (int k = p; k <= r; k++) {
            if (i == n1) {
                A[k] = R[j];
                j++;
            } else if (j == n2) {
                A[k] = L[i];
                i++;
            } else if (L[i] > R[j]) {
                A[k] = R[j];
                inversions += n1 - i;
                j++;
            } else {
                A[k] = L[i];
                i++;
            }
        }
        return inversions;
    }
}
