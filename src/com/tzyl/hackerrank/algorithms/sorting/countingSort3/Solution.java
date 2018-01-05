package com.tzyl.hackerrank.algorithms.sorting.countingSort3;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] counts = new int[100];
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            String s = sc.next();
            counts[x]++;
        }
        int cumulativeCount = 0;
        for (int i = 0; i < counts.length; i++) {
            cumulativeCount += counts[i];
            System.out.print(cumulativeCount + " ");
        }
    }
}
