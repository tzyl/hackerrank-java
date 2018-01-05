package com.tzyl.hackerrank.weekofcode.w34.maximumGcdAndSum;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int maximumGcdAndSum(int[] A, int[] B) {
        boolean[] A_check = new boolean[1000001];
        boolean[] B_check = new boolean[1000001];
        for (int i = 0; i < A.length; i++) {
            A_check[A[i]] = true;
            B_check[B[i]] = true;
        }
        // Try each divisor and test if it's a possible gcd.
        int result = 0;
        for (int d = 1; d <= 1000000; d++) {
            int bestFromA = -1;
            int bestFromB = -1;
            for (int i = 1; i <= 1000000 / d; i++) {
                int x = i * d;
                if (A_check[x]) bestFromA = x;
                if (B_check[x]) bestFromB = x;
            }
            if (bestFromA != -1 && bestFromB != -1) {
                result = bestFromA + bestFromB;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int A_i = 0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int[] B = new int[n];
        for(int B_i = 0; B_i < n; B_i++){
            B[B_i] = in.nextInt();
        }
        int res = maximumGcdAndSum(A, B);
        System.out.println(res);
    }
}
