package com.tzyl.hackerrank.algorithms.dynamicprogramming.theCoinChangeProblem;

import java.io.*;
import java.util.*;

// O(NM) time, O(NM) space (can change to O(N) space).
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        // numberOfWays[i][j] is the number of ways using the first i coins
        // to make j change.
        long[][] numberOfWays = new long[M + 1][N + 1];
        int[] coins = new int[M];
        for (int i = 0; i < M; i++) {
            coins[i] = sc.nextInt();
        }
        numberOfWays[0][0] = 1;
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                // We can either add another of the ith coin or not use any ith coins.
                numberOfWays[i][j] = numberOfWays[i - 1][j];
                if (j - coins[i - 1] >= 0) {
                    numberOfWays[i][j] += numberOfWays[i][j - coins[i - 1]];
                }
            }
        }
        System.out.println(numberOfWays[M][N]);
    }
}
