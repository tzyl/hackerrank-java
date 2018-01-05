package com.tzyl.hackerrank.datastructures.trees.swapNodes;

import java.io.*;
import java.util.*;

public class Solution {
    public static int[] left;
    public static int[] right;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        left = new int[N];
        right = new int[N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // We use a - 1 and b - 1 to be zero-indexed.
            left[i] = (a != -1) ? a - 1 : -1;
            right[i] = (b != -1) ? b - 1 : -1;
        }
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int K = sc.nextInt();
            reverse(0, 1, K);
            inOrderTraversal(0);
            System.out.println();
        }
    }

    public static void reverse(int i, int currentDepth, int K) {
        // Reverses subtrees of nodes at depth K of tree rooted at i.
        if (i != -1) {
            if (currentDepth % K == 0) {
                int temp = left[i];
                left[i] = right[i];
                right[i] = temp;
            }
            reverse(left[i], currentDepth + 1, K);
            reverse(right[i], currentDepth + 1, K);
        }
    }

    public static void inOrderTraversal(int i) {
        if (i != -1) {
            inOrderTraversal(left[i]);
            // i + 1 as we store using zero index.
            System.out.print(i + 1 + " ");
            inOrderTraversal(right[i]);
        }
    }
}
