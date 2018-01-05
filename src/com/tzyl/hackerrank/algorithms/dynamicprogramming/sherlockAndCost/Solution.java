package com.tzyl.hackerrank.algorithms.dynamicprogramming.sherlockAndCost;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] B = new int[N];
            for (int i = 0; i < N; i++) {
                B[i] = sc.nextInt();
            }
            // For each i we either pick the lowest or the highest.
            // Keep track of best cost by picking current lowest or highest.
            int pickLow = 0;
            int pickHigh = 0;
            for (int i = 1; i < N; i++) {
                int newPickHigh = pickHigh;
                int newPickLow = pickLow;
                if (pickLow + B[i] - 1 > pickHigh) {
                    newPickHigh = pickLow + B[i] - 1;
                }
                if (pickHigh + B[i - 1] - 1 > pickLow) {
                    newPickLow = pickHigh + B[i - 1] - 1;
                }
                pickLow = newPickLow;
                pickHigh = newPickHigh;
            }
            System.out.println((pickLow > pickHigh) ? pickLow : pickHigh);
        }
    }
}
