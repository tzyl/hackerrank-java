package com.tzyl.hackerrank.algorithms.dynamicprogramming.constructTheArray;

import java.io.*;
import java.util.*;

public class Solution {
    private static final long MOD = 1000000007;

    /**
     * Counts the number of arrays of length n, containing only elements between 1 and k, beginning with 1
     * and ending with x, with no consecutive elements equal.
     *
     * We have the recurrence relation:
     *
     * For x != 1
     * f(2) = 1
     * f(n) = (k - 1) ^ (n - 2) -  f(n - 1)
     *      = (k - 1) ^ (n - 2) -  (k - 1) ^ (n - 3) + f(n - 2)
     *      .
     *      .
     *      = ((k - 1) ^ (n - 1) + (-1) ^ n) / k
     * i.e. all ways to make an array of length n - 1 take away those ending in x.
     *
     * For x = 1
     * g(2) = 0
     * g(n) = (k - 1) * f(n - 1)
     * i.e. all ways of not ending with 1 on penultimate element
     *
     * Can calculate the closed form expression in O(log(n)) time with modular exponentiation.
     *
     * @param n the length of the target array
     * @param k the max number an element in the array can be
     * @param x the ending element of the target array
     * @return the number of possible arrays satisfying the conditions modulo 1000000007
     */
    static long countArray(int n, int k, int x) {
        return (x != 1) ? f(n, k) : g(n, k);
    }

    private static long f(int n, int k) {
        if (n == 2) {
            return 1;
        }
        long a = pow(k - 1, n - 1, MOD);
        long b = (n % 2 == 1) ? -1 : 1;
        // Fermat's little theorem
        long kInverse = pow(k, MOD - 2, MOD);

        return ((a + b) * kInverse) % MOD;
    }

    private static long g(int n, int k) {
        if (n == 2) {
            return 0;
        }
        return ((k - 1) * f(n - 1, k)) % MOD;
    }

    private static long pow(long x, long y, long mod) {
        if (y == 0) {
            return 1;
        } else if (y % 2 == 1) {
            return (x * pow(x, y - 1, mod)) % mod;
        } else {
            long sqrt = pow(x, y / 2, mod);
            return (sqrt * sqrt) % mod;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nkx = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nkx[0]);

        int k = Integer.parseInt(nkx[1]);

        int x = Integer.parseInt(nkx[2]);

        long answer = countArray(n, k, x);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
