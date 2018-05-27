package com.tzyl.hackerrank.worldcodesprint.wcs13.watsonsLoveForArrays;

import java.io.*;
import java.util.*;

public class Solution {

    /**
     * Optimized brute force O(n^2).
     *
     * Timeout on 3 test cases.
     */
    public static long howManyGoodSubarrays(int[] A, int m, int k) {
        Map<Long, Long> counts = new HashMap<>();
        long total = 0;

        for (int i = 0; i < A.length; i++) {
            long current = A[i] % m;
            Map<Long, Long> newCounts = new HashMap<>();

            // Subarrays ending at previous element combined with the current element.
            counts.forEach((product, count) -> {
                long newProduct = (product * current) % m;
                newCounts.put(newProduct, newCounts.getOrDefault(newProduct, 0L) + count);
            });
            // Single element subarray
            newCounts.put(current, newCounts.getOrDefault(current, 0L) + 1);

            counts = newCounts;
            total += counts.getOrDefault((long) k, 0L);
        }

        return total;
    }

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(in.readLine());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nmk = in.readLine().split(" ");

            int n = Integer.parseInt(nmk[0]);

            int m = Integer.parseInt(nmk[1]);

            int k = Integer.parseInt(nmk[2]);

            int[] A = new int[n];

            String[] AItems = in.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            long result = howManyGoodSubarrays(A, m, k);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        in.close();
    }
}
