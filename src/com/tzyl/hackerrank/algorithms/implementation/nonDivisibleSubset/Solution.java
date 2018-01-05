package com.tzyl.hackerrank.algorithms.implementation.nonDivisibleSubset;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] S = new int[n];
        int[] remainderCounts = new int[k];
        for (int i = 0; i < n; i++) {
            S[i] = sc.nextInt();
            remainderCounts[S[i] % k]++;
        }
        int maximumSubsetSize = 0;
        int i = 1;
        while (i < k - i) {
            // We can't have elements in the subset which are i and k - i modulo k
            // otherwise added together they are divisible by k.
            if (remainderCounts[i] > remainderCounts[k - i]) {
                maximumSubsetSize += remainderCounts[i];
            } else {
                maximumSubsetSize += remainderCounts[k - i];
            }
            i++;
        }
        // Check edge cases 0 and k / 2 (if k even) modulo k which are only allowed one of.
        if (remainderCounts[0] > 0) maximumSubsetSize++;
        if (k % 2 == 0 && remainderCounts[k / 2] > 0) maximumSubsetSize++;
        System.out.println(maximumSubsetSize);
    }
}
