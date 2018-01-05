package com.tzyl.hackerrank.algorithms.dynamicprogramming.primeXOR;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static ArrayList<Integer> primes = initializePrimes(8192);
    static int MOD = 1000000007;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int testCase = 0; testCase < q; testCase++) {
            int n = sc.nextInt();
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }
            System.out.println(solve(A));
        }
    }

    static long solve(int[] A) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int x = A[i];
            if (!counter.containsKey(x)) {
                counter.put(x, 1);
                uniqueNumbers.add(x);
            } else {
                counter.put(x, counter.get(x) + 1);
            }
        }
        // dp[i][j] contains number of multisets formed using
        // first i unique numbers which xor to j.
        int m = counter.size();
        long[][] dp = new long[m + 1][8192];
        // Initialize first row. One way to choose empty set.
        dp[0][0] = 1;
        // Fill in other rows.
        for (int i = 1; i <= m; i++) {
            int x = uniqueNumbers.get(i - 1);
            int occurrences = counter.get(x);
            for (int j = 0; j < 8192; j++) {
                int y = j ^ x;
                // We either include an even or odd number of occurrences of x.
                // If odd then they will xor to x, if even then they will xor to 0.
                // Even case.
                dp[i][j] = dp[i - 1][j] * (1 + occurrences / 2);
                if (y < 8192) {
                    // Odd case.
                    dp[i][j] += dp[i - 1][y] * ((occurrences + 1) / 2);
                }
                dp[i][j] = dp[i][j] % MOD;
            }
        }
        long total = 0;
        for (int prime : primes) {
            total = (total + dp[m][prime]) % MOD;
        }
        return total;
    }

    // Returns a list of all primes < max.
    static ArrayList<Integer> initializePrimes(int max) {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[max];
        for (int x = 2; x < max; x++) {
            isPrime[x] = true;
        }
        for (int x = 2; x < max; x++) {
            if (isPrime[x]) {
                primes.add(x);
            }
            for (int y = x; y < max; y += x) {
                isPrime[y] = false;
            }
        }
        return primes;
    }
}

