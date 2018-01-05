package com.tzyl.hackerrank.algorithms.sorting.quicksort1;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        partition(A, 0, A.length - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static int partition(int[] A, int p, int r) {
        // We pivot on first element for this question.
        // Swap to end to use standard Lomuto method.
        int pivot = A[p];
        swap(A, p, r);
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
