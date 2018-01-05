package com.tzyl.hackerrank.weekofcode.w34.sameOccurrence;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Optimized to traverse only occurrences.
// Uses BufferedReader for input, StringBuilder for output.
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = in.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int q = Integer.parseInt(tokens[1]);
        // Scanner in = new Scanner(System.in);
        // int n = in.nextInt();
        // int q = in.nextInt();
        int[] A = new int[n];
        tokens = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(tokens[i]);
            // A[i] = in.nextInt();
        }
        // Find indexes of occurrences of each number in A.
        HashMap<Integer, ArrayList<Integer>> occurrences = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!occurrences.containsKey(A[i])) {
                occurrences.put(A[i], new ArrayList<>());
            }
            occurrences.get(A[i]).add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int testCase = 0; testCase < q; testCase++) {
            tokens = in.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            // int x = in.nextInt();
            // int y = in.nextInt();
            ArrayList<Integer> xOccurrences = occurrences.containsKey(x) ? occurrences.get(x) : new ArrayList<>();
            ArrayList<Integer> yOccurrences = occurrences.containsKey(y) ? occurrences.get(y) : new ArrayList<>();
            sb.append(countSubarrays(n, xOccurrences, yOccurrences)).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static int countSubarrays(int n, ArrayList<Integer> xOccurrences, ArrayList<Integer> yOccurrences) {
        int result = 0;
        // differenceCount maps difference between x count and y count
        // to the number of such initial segment subarrays.
        HashMap<Integer, Integer> differenceCount = new HashMap<>();
        int difference = 0;
        // Now traverse occurrences of x and y in order updating differenceCount.
        int n1 = xOccurrences.size();
        int n2 = yOccurrences.size();
        int i = 0;
        int j = 0;
        // Start at -1 to represent the empty initial segment having difference 0.
        int currentIndex = -1;
        for (int occurrence = 0; occurrence < n1 + n2; occurrence++) {
            int newIndex = currentIndex;
            int newDifference = difference;
            if (i == n1) {
                // Used up all x occurrences.
                newDifference--;
                newIndex = yOccurrences.get(j);
                j++;
            } else if (j == n2) {
                // Used up all y occurrences.
                newDifference++;
                newIndex = xOccurrences.get(i);
                i++;
            } else if (xOccurrences.get(i) < yOccurrences.get(j)) {
                newDifference++;
                newIndex = xOccurrences.get(i);
                i++;
            } else {
                newDifference--;
                newIndex = yOccurrences.get(j);
                j++;
            }
            // Update how many indices we traversed on the old difference.
            differenceCount.merge(difference, newIndex - currentIndex, (a, b) -> a + b);
            currentIndex = newIndex;
            difference = newDifference;
        }
        // Final difference.
        differenceCount.merge(difference, n - currentIndex, (a, b) -> a + b);
        // All valid subarrays can be created from the difference of two
        // initial segment subarrays with equal x and y count differences.
        for (Integer count : differenceCount.values()) {
            result += count * (count - 1) / 2;
        }
        return result;
    }
}


// O(n*q)
// public class DuplicateWords {
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int n = in.nextInt();
//         int q = in.nextInt();
//         int[] A = new int[n];
//         for (int i = 0; i < n; i++) {
//             A[i] = in.nextInt();
//         }
//         // StringBuilder sb = new StringBuilder();
//         for (int testCase = 0; testCase < q; testCase++) {
//             int x = in.nextInt();
//             int y = in.nextInt();
//             System.out.println(countSubarrays(A, x, y));
//             // sb.append(countSubarrays(A, x, y)).append('\n');
//         }
//         // System.out.print(sb.toString());
//     }
//
//     public static int countSubarrays(int[] A, int x, int y) {
//         int n = A.length;
//         // differenceCount maps difference between x count and y count
//         // to the number of such initial segment subarrays.
//         HashMap<Integer, Integer> differenceCount = new HashMap<>();
//         // Add empty initial segment subarray.
//         differenceCount.put(0, 1);
//         int difference = 0;
//         int result = 0;
//         for (int i = 0; i < n; i++) {
//             if (A[i] == x) difference++;
//             if (A[i] == y) difference--;
//             // Increment count of difference.
//             differenceCount.merge(difference, 1, (a, b) -> a + b);
//         }
//         // All valid subarrays can be created from the difference of two
//         // initial segment subarrays with equal x and y count differences.
//         for (Integer count : differenceCount.values()) {
//             result += count * (count - 1) / 2;
//         }
//         return result;
//     }
// }

// Brute force O(n^2 * q)
// public class DuplicateWords {
//
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int n = in.nextInt();
//         int q = in.nextInt();
//         int[] A = new int[n];
//         for(int A_i = 0; A_i < n; A_i++){
//             A[A_i] = in.nextInt();
//         }
//         for(int a0 = 0; a0 < q; a0++){
//             int x = in.nextInt();
//             int y = in.nextInt();
//             int subArrayCount = 0;
//             // Brute force check all subarrays.
//             for (int i = 0; i < n; i++) {
//                 int xCount = 0;
//                 int yCount = 0;
//                 for (int j = i; j < n; j++) {
//                     if (A[j] == x) xCount++;
//                     if (A[j] == y) yCount++;
//                     if (xCount == yCount) subArrayCount++;
//                 }
//             }
//             System.out.println(subArrayCount);
//         }
//         in.close();
//     }
// }
