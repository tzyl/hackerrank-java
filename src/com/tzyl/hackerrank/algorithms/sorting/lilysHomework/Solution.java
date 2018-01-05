package com.tzyl.hackerrank.algorithms.sorting.lilysHomework;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        HashMap<Integer, Integer> sortedIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        // Calculate the correct index for each element of A in sorted order.
        for (int i = 0; i < n; i++) {
            B[i] = A[i];
        }
        Arrays.sort(B);
        for (int i = 0; i < n; i++) {
            sortedIndex.put(B[i], i);
        }
        // Count how many swaps to put A into sorted order.
        for (int i = 0; i < n; i++) {
            B[i] = A[i];
        }
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int correctIndex = sortedIndex.get(B[i]);
            while (i != correctIndex) {
                swap(B, i, correctIndex);
                swaps++;
                correctIndex = sortedIndex.get(B[i]);
            }
        }
        // Count how many swaps to put A into reverse sorted order.
        for (int i = 0; i < n; i++) {
            B[i] = A[i];
        }
        int reversedSwaps = 0;
        for (int i = 0; i < n; i++) {
            int correctIndex = n - 1 - sortedIndex.get(B[i]);
            while (i != correctIndex) {
                swap(B, i, correctIndex);
                reversedSwaps++;
                correctIndex = n - 1 - sortedIndex.get(B[i]);
            }
        }
        System.out.println(swaps < reversedSwaps ? swaps : reversedSwaps);
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
