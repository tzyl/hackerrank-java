package com.tzyl.hackerrank.algorithms.sorting.countingSort2;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] counts = new int[100];
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            counts[sc.nextInt()]++;
        }
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            for (int j = 0; j < count; j++) {
                System.out.print(i + " ");
            }
        }
    }
}
