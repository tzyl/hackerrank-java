package com.tzyl.hackerrank.weekofcode.w31.spanningTreeFraction;

import java.io.*;
import java.util.*;

public class Solution {
    static int[] U;
    static int[] V;
    static int[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // int n = in.nextInt();
        // int m = in.nextInt();
        String[] tokens = in.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);

        U = new int[m];
        V = new int[m];
        A = new int[m];
        B = new int[m];

        for (int i = 0; i < m; i++) {
            // int u = in.nextInt();
            // int v = in.nextInt();
            // int a = in.nextInt();
            // int b = in.nextInt();
            tokens = in.readLine().split(" ");
            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            int a = Integer.parseInt(tokens[2]);
            int b = Integer.parseInt(tokens[3]);
            if (u != v) {
                // Skip self-loops which cannot be in a tree.
                U[i] = u;
                V[i] = v;
                A[i] = a;
                B[i] = b;
            }
        }
        // Binary search on possible value for the maximum spanning tree fraction.
        // sum(a_i)/sum(b_i) >= C => sum(a_i - C * b_i) >= 0.
        double low = 0;
        double high = 100;
        int maxIterations = 60;
        double epsilon = 0.0001;
        int iteration = 0;
        int bestNumerator = 1;
        int bestDenominator = 1;

        while (high - low > epsilon && iteration < maxIterations) {
            double C = (low + high) / 2;
            int sumA = 0;
            int sumB = 0;

            // Run Kruskal to find maximum spanning tree with weights a_i - C * b_i.
            DisjointSetForest vertexComponents = new DisjointSetForest(n);
            // Sort edges by weight a_i - C * b_i in decreasing order.
            Integer[] indices = new Integer[m];
            for (int i = 0; i < m; i++) {
                indices[i] = i;
            }
            Arrays.sort(indices, Comparator.comparing((Integer i) -> A[i] - C * B[i])
                                           .reversed());
            // Build maximum spanning tree.
            for (int i = 0; i < m; i++) {
                int u = U[indices[i]];
                int v = V[indices[i]];
                int a = A[indices[i]];
                int b = B[indices[i]];

                if (vertexComponents.find(u) != vertexComponents.find(v)) {
                    vertexComponents.union(u, v);
                    sumA += a;
                    sumB += b;
                }
            }
            // Check if it was possible to create a spanning tree with value >= C.
            if (sumA >= C * sumB) {
                low = C;
                bestNumerator = sumA;
                bestDenominator = sumB;
            } else {
                high = C;
            }
            iteration++;
        }

        int c = gcd(bestNumerator, bestDenominator);
        System.out.printf("%d/%d", bestNumerator / c, bestDenominator / c);
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

class DisjointSetForest {
    int[] parent;
    int[] rank;

    public DisjointSetForest(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);

        if (root1 == root2) return;

        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            parent[root1] = root2;
            if (rank[root1] == rank[root2]) {
                rank[root2]++;
            }
        }
    }
}