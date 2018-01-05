package com.tzyl.hackerrank.algorithms.dynamicprogramming.kingdomDivision;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static long[][][] dp;
    private static TreeNode[] vertexNodes;
    private static final int mod = 1000000007;

    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();
        for (int vertex = 0; vertex < n; vertex++) {
            adjacencyList.add(new LinkedList<Integer>());
        }
        for (int edgeIndex = 0; edgeIndex < n - 1; edgeIndex++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // Change to 0 index for convenience of adjacency list.
            adjacencyList.get(u - 1).add(v - 1);
            adjacencyList.get(v - 1).add(u - 1);
        }

        vertexNodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            vertexNodes[i] = new TreeNode(i);
        }

        // DFS to build tree.
        // Root the tree at vertex 0 (arbitrary choice).
        boolean[] seen = new boolean[n];
        Deque<TreeNode> stack = new LinkedList<>();
        seen[0] = true;
        stack.addFirst(vertexNodes[0]);
        while (!stack.isEmpty()) {
            TreeNode u = stack.removeFirst();
            for (int i : adjacencyList.get(u.key)) {
                if (!seen[i]) {
                    TreeNode v = vertexNodes[i];
                    u.children.add(v);
                    v.parent = u;
                    stack.addFirst(v);
                    seen[i] = true;
                }
            }
        }

        // Colour the vertices either 0 or 1.
        // We may assume that root has a parent of opposite colour for
        // convenience as this will not affect the number of possible ways.
        dp = new long[n][2][2];
        long total = (dfs(0, 0, 1) + dfs(0, 1, 0)) % mod;
        System.out.println(total);
    }

    private static long dfs(int vertex, int currentColour, int parentColour) {
        // Check if already cached result.
        if (dp[vertex][currentColour][parentColour] != 0) {
            return dp[vertex][currentColour][parentColour];
        }

        // val0 is the total number of ways where all children are colour 0.
        // val1 is the total number of ways where all children are colour 1.
        long total = 1;
        long val0 = 1;
        long val1 = 1;
        TreeNode u = vertexNodes[vertex];
        for (TreeNode child : u.children) {
            // a0 is the number of ways where child is colour 0.
            // a1 is the number of ways where child is colour 1.
            long a0 = dfs(child.key, 0, currentColour);
            long a1 = dfs(child.key, 1, currentColour);
            // Then val0 and val1 are obtained by multiplying all combinations
            // of the ways of arranging each child node.
            val0 = val0 * a0 % mod;
            val1 = val1 * a1 % mod;
            // Similarly total ways obtained from child being either colour.
            total = total * (a0 + a1) % mod;
        }

        // If currentColour == parentColour then this number of ways is correct.
        // If currentColour != parentColour then we have over counted by val0 or val1
        // respectively because we require current vertex to be the same colour of at
        // least one of its children.
        if (currentColour != parentColour) {
            // We add mod to ensure that the result is positive. (In Java % gives the
            // same sign as the dividend.)
            if (currentColour == 0) {
                total = (total - val1 + mod) % mod;
            } else if (currentColour == 1) {
                total = (total - val0 + mod) % mod;
            }
        }

        // Cache result in dp array.
        dp[vertex][currentColour][parentColour] = total;
        return total;
    }
}

class TreeNode {
    public int key;
    public TreeNode parent;
    public LinkedList<TreeNode> children = new LinkedList<>();

    public TreeNode(int key) {
        this.key = key;
    }
}