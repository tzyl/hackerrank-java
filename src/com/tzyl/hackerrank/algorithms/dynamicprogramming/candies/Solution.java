package com.tzyl.hackerrank.algorithms.dynamicprogramming.candies;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] ratings = new int[N];
        int[] candies = new int[N];
        for (int i = 0; i < N; i++) {
            ratings[i] = sc.nextInt();
            candies[i] = 1;
        }
        // Go from left to right setting number of candies to be one more than
        // the previous number of candies if the rating increases.
        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // Go from right to left for decreases in rating similarly.
        // We only update the number if it is more than what was set for the
        // rating increases to keep arrangement valid.
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                if (candies[i + 1] + 1 > candies[i]) {
                    candies[i] = candies[i + 1] + 1;
                }
            }
        }
        // We now have an optimal arrangement. Print out the sum of candies.
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += candies[i];
        }
        System.out.println(sum);
    }
}
