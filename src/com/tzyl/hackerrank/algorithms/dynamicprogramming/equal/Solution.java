package com.tzyl.hackerrank.algorithms.dynamicprogramming.equal;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            int min = 1000;
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
                if (A[i] < min) {
                    min = A[i];
                }
            }
            // Loop through and calculate how many operations we need to
            // reach min by subtracting 1, 2 or 5 at a time.
            // We have 5 possible candidate minimums to try.
            int minOperations = Integer.MAX_VALUE;
            for (int k = 0; k < 5; k++) {
                int newMin = min - k;
                int operations = 0;
                for (int i = 0; i < N; i++) {
                    int difference = A[i] - newMin;
                    while (difference >= 5) {
                        operations++;
                        difference -= 5;
                    }
                    while (difference >= 2) {
                        operations++;
                        difference -= 2;
                    }
                    while (difference >= 1) {
                        operations++;
                        difference -= 1;
                    }
                }
                if (operations < minOperations) {
                    minOperations = operations;
                }
            }
            System.out.println(minOperations);
        }
    }
}
