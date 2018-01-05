package com.tzyl.hackerrank.datastructures.stacks.gameOfTwoStacks;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // int g = in.nextInt();
        int g = Integer.parseInt(in.readLine());
        for(int a0 = 0; a0 < g; a0++){
            String[] temp;
            String[] constraints = in.readLine().split(" ");
            int n = Integer.parseInt(constraints[0]);
            int m = Integer.parseInt(constraints[1]);
            int x = Integer.parseInt(constraints[2]);
            // int n = in.nextInt();
            // int m = in.nextInt();
            // int x = in.nextInt();
            int[] a = new int[n];
            temp = in.readLine().split(" ");
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = Integer.parseInt(temp[a_i]);
                // a[a_i] = in.nextInt();
            }
            int[] b = new int[m];
            temp = in.readLine().split(" ");
            for(int b_i=0; b_i < m; b_i++){
                b[b_i] = Integer.parseInt(temp[b_i]);
                // b[b_i] = in.nextInt();
            }
            // Calculate best score using 0, 1,..., n of the integers from stack a.
            int sum = 0;
            int aCount = 0;
            int bCount = 0;
            while (bCount < m && sum + b[bCount] <= x) {
                sum += b[bCount];
                bCount++;
            }
            int bestScore = bCount;
            for (int i = 0; i < n; i++) {
                sum += a[aCount];
                aCount++;
                while (bCount > 0 && sum > x) {
                    bCount--;
                    sum -= b[bCount];
                }
                if (sum <= x && aCount + bCount > bestScore) {
                    bestScore = aCount + bCount;
                }
            }
            System.out.println(bestScore);
        }
    }
}
