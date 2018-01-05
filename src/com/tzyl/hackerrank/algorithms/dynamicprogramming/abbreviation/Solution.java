package com.tzyl.hackerrank.algorithms.dynamicprogramming.abbreviation;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            String a = sc.next();
            String b = sc.next();
            System.out.println(isAbbreviation(a, b) ? "YES" : "NO");
        }
    }

    // Returns true if a can be transformed to b by capitalizing any of the
    // lowercase letters then discarding all remaining lowercase letters.
    public static boolean isAbbreviation(String a, String b) {
        int m = a.length();
        int n = b.length();
        if (m < n) return false;
        // dp[i][j] = true if a[:i] can be transformed to b[:j].
        boolean[][] dp = new boolean[m + 1][n + 1];
        // Initialize first column of dp matrix. To transform a[:i] to the
        // empty string we need a[:i] to all be lowercase.
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (Character.isLowerCase(a.charAt(i - 1))) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = false;
            }
        }
        // Fill in rest of dp table.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Choose to capitalize the ith letter of a (or leave it uppercase).
                if (Character.toUpperCase(a.charAt(i - 1)) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // Choose to discard lowercase letter ith letter of a.
                if (!dp[i][j] && Character.isLowerCase(a.charAt(i - 1))) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}

// You can perform the following operation on some string, :
//
//     Capitalize zero or more of 's lowercase letters at some index i (i.e., make them uppercase).
//     Delete all of the remaining lowercase letters in .
//     Given  queries in the form of two strings,  and , determine if it's possible to make  equal to  by performing the above operation on . If  can be transformed into , print YES on a new line; otherwise, print NO.
//
//     Input Format
//
//     The first line contains a single integer, , denoting the number of queries. The  subsequent lines describe each query in the following format:
//
//     The first line of each query contains a single string, .
//     The second line of each query contains a single string, .
//     Constraints
//
//     String  consists only of uppercase and lowercase English letters.
//     String  consists only of uppercase English letters.
//     Output Format
//
//     For each query, print YES on a new line if it's possible to make string  equal to string  by performing the operation specified above; otherwise, print NO.
//
//     Sample Input
//
//     1
//     daBcd
//     ABC
//     Sample Output
//
//     YES
//     Explanation
//
//     We have  daBcd and  ABC. We perform the following opera