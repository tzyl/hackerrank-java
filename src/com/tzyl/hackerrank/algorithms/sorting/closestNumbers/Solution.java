package com.tzyl.hackerrank.algorithms.sorting.closestNumbers;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        Arrays.sort(A);
        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            if (A[i] - A[i - 1] < smallestDifference) {
                smallestDifference = A[i] - A[i - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            if (A[i] - A[i - 1] == smallestDifference) {
                System.out.print(A[i - 1] + " " + A[i] + " ");
            }
        }
    }
}
