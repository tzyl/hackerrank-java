package com.tzyl.hackerrank.algorithms.sorting.countingSort4;

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] indexes = new int[n];
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            String[] tokens = in.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            // int x = sc.nextInt();
            indexes[i] = x;
            String s = tokens[1];
            // String s = sc.next();
            strings[i] = (i < n / 2) ? "-" : s;
        }
        // Counting sort.
        int[] counts = new int[100];
        String[] sortedStrings = new String[n];
        // Count indexes and make cumulative counts.
        for (int i = 0; i < indexes.length; i++) {
            counts[indexes[i]]++;
        }

        int cumulativeCount = 0;
        for (int i = 0; i < counts.length; i++) {
            int oldCount = counts[i];
            counts[i] = cumulativeCount;
            cumulativeCount += oldCount;
        }

        for (int i = 0; i < indexes.length; i++) {
            sortedStrings[counts[indexes[i]]] = strings[i];
            counts[indexes[i]]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(sortedStrings[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}
