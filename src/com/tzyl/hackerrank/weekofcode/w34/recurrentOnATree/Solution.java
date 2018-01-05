package com.tzyl.hackerrank.weekofcode.w34.recurrentOnATree;

import java.io.*;
import java.util.*;

public class Solution {
    private static final int MOD = 1000000007;
    // private static TreeNode[] vertexNodes;
    private static int[] c;
    private static long fibonacciSum;

    public static void main(String[] args) {
        fibonacciSum = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        LinkedList<LinkedList<Integer>> adjacencyList = new LinkedList<>();
        for (int vertex = 0; vertex < n; vertex++) {
            adjacencyList.add(new LinkedList<>());
        }
        for (int edgeIndex = 0; edgeIndex < n - 1; edgeIndex++) {
            int u = in.nextInt();
            int v = in.nextInt();
            // Change to 0 index for convenience of adjacency list.
            adjacencyList.get(u - 1).add(v - 1);
            adjacencyList.get(v - 1).add(u - 1);
        }

        // Set vertex weights.
        c = new int[n];
        for (int vertex = 0; vertex < n; vertex++) {
            c[vertex] = in.nextInt();
        }

        // Create nodes for each of the vertices.
        TreeNode[] vertexNodes = new TreeNode[n];
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

        // Recursively solve for paths rooted at each node.
        solve(vertexNodes[0]);
        System.out.println(fibonacciSum);
    }

    // Returns sums of all paths starting from root.
    // Adds contribution of all paths going through root to fibonacciSum.
    // We add 1 to the argument when calculating fibonacci due to the
    // different indexing used in this problem statement.

    // Optimize to use counts to avoid duplicates.
    private static HashMap<Integer, Integer> solve(TreeNode root) {
        HashMap<Integer, Integer> pathSumCount = new HashMap<>();
        int current = c[root.key];
        // Store pathSumCounts of children.
        int m = root.children.size();
        ArrayList<HashMap<Integer, Integer>> childrenPathSumCounts = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            childrenPathSumCounts.add(solve(root.children.get(i)));
        }
        // Add single paths connecting to the root.
        for (int i = 0; i < m; i++) {
            HashMap<Integer, Integer> childPathSumCount = childrenPathSumCounts.get(i);
            for (HashMap.Entry<Integer, Integer> entry : childPathSumCount.entrySet()) {
                int pathSum = entry.getKey();
                int count = entry.getValue();
                increment(pathSumCount, pathSum + current, count);
                // fibonacciSum = (fibonacciSum + 2 * fibonacciModulo(current + pathSum + 1)) % MOD;
            }
        }
        // Copy pathSumCount so we can add more counts for efficient fibonacci calculations.
        HashMap<Integer, Integer> fibonacciCount = new HashMap<>(pathSumCount);
        // Add paths passing through the root (not starting from).
        // We don't add to pathSums as these do not start from root.
        for (int i = 0; i < m; i++) {
            HashMap<Integer, Integer> childPathSumCount1 = childrenPathSumCounts.get(i);
            for (int j = i + 1; j < m; j++) {
                HashMap<Integer, Integer> childPathSumCount2 = childrenPathSumCounts.get(j);
                for (HashMap.Entry<Integer, Integer> entry1 : childPathSumCount1.entrySet()) {
                    for (HashMap.Entry<Integer, Integer> entry2 : childPathSumCount2.entrySet()) {
                        int pathSum1 = entry1.getKey();
                        int pathSum2 = entry2.getKey();
                        int count1 = entry1.getValue();
                        int count2 = entry2.getValue();
                        increment(fibonacciCount, pathSum1 + pathSum2 + current, count1 * count2);
                        // fibonacciSum = (fibonacciSum + 2 * fibonacciModulo(current + pathSum1 + pathSum2 + 1)) % MOD;
                    }
                }
            }
        }
        // Now calculate the contribution to fibonacciSum remembering that paths are include twice except
        // for the single node path of root to itself.
        for (HashMap.Entry<Integer, Integer> entry : fibonacciCount.entrySet()) {
            // Add 1 to account for different definition of sequence in this problem.
            int index = entry.getKey() + 1;
            int count = entry.getValue();
            fibonacciSum = (fibonacciSum + 2 * count * fibonacciModulo(index)) % MOD;
        }
        // Add path of root to itself.
        increment(pathSumCount, current);
        fibonacciSum = (fibonacciSum + fibonacciModulo(current + 1)) % MOD;
        return pathSumCount;
    }

    // private static LinkedList<Integer> solve(TreeNode root) {
    //     LinkedList<Integer> pathSums = new LinkedList<>();
    //     // Add path of root to itself.
    //     int current = c[root.key];
    //     pathSums.add(current);
    //     fibonacciSum = (fibonacciSum + fibonacciModulo(current + 1)) % MOD;
    //     // Store pathSums of children.
    //     int m = root.children.size();
    //     ArrayList<LinkedList<Integer>> childrenPathSums = new ArrayList<>();
    //     for (int i = 0; i < m; i++) {
    //         childrenPathSums.add(solve(root.children.get(i)));
    //     }
    //     // Add single paths connecting to the root.
    //     for (int i = 0; i < m; i++) {
    //         for (int pathSum : childrenPathSums.get(i)) {
    //             pathSums.add(current + pathSum);
    //             fibonacciSum = (fibonacciSum + 2 * fibonacciModulo(current + pathSum + 1)) % MOD;
    //         }
    //     }
    //     // Add paths passing through the root (not starting from).
    //     // We don't add to pathSums as these do not start from root.
    //     for (int i = 0; i < m; i++) {
    //         for (int j = i + 1; j < m; j++) {
    //             for (int pathSum1 : childrenPathSums.get(i)) {
    //                 for (int pathSum2 : childrenPathSums.get(j)) {
    //                     fibonacciSum = (fibonacciSum + 2 * fibonacciModulo(current + pathSum1 + pathSum2 + 1)) % MOD;
    //                 }
    //             }
    //         }
    //     }
    //     return pathSums;
    // }

    // Increments the value at key or initializes to 1 if key does not exist.
    private static void increment(HashMap<Integer, Integer> map, int key) {
        increment(map, key, 1);
    }

    private static void increment(HashMap<Integer, Integer> map, int key, int amount) {
        if (!map.containsKey(key)) {
            map.put(key, amount);
        } else {
            map.put(key, map.get(key) + amount);
        }
    }

    /*
    * Returns the nth fibonacci number modulo MOD.
    * The definition here is F_1 = 1, F_2 = 1, F_n = F_n-1 + F_n-2.
    * We use matrix multiplication method.
    * */
    private static long fibonacciModulo(int n) {
        // Initial vector.
        long[] F_1 = new long[2];
        F_1[0] = 1;
        F_1[1] = 1;
        // Transformation matrix.
        long[][] T = new long[2][2];
        T[0][0] = 0;
        T[0][1] = 1;
        T[1][0] = 1;
        T[1][1] = 1;
        // Raise T to (n - 1)th power.
        if (n == 1) return 1;
        long[][] T_pow = pow(T, n - 1);
        // Get result from first entry of T^(n-1) . F_1.
        long result = 0;
        for (int i = 0; i < 2; i++) {
            result = (result + T_pow[0][i] * F_1[i]) % MOD;
        }
        return result;
    }

    // Raises 2x2 matrix T to power p modulo MOD.
    private static long[][] pow(long[][] T, int p) {
        if (p == 1) return T;
        if (p % 2 == 1) return mul(T, pow(T, p - 1));
        long[][] T_sqrt = pow(T, p / 2);
        return mul(T_sqrt, T_sqrt);
    }

    // Multiplies 2x2 matrices A and B modulo MOD.
    private static long[][] mul(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}

class TreeNode {
    public int key;
    public TreeNode parent;
    public ArrayList<TreeNode> children = new ArrayList<>();
    public TreeNode(int key) {
        this.key = key;
    }
}