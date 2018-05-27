package com.tzyl.hackerrank.worldcodesprint.wcs13.groupFormation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    /**
     * O(n + q) using disjoint set forest for near constant time union.
     */
    public static void membersInTheLargestGroups(int n, int m, int a, int b, int f, int s, int t) {
        Map<String, Integer> nameIndex = new HashMap<>(n);
        String[] names = new String[n];
        int[] grades = new int[n];

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            nameIndex.put(name, i);
            names[i] = name;
            // Zero index grade for convenience.
            int grade = scanner.nextInt();
            grades[i] = grade - 1;
        }

        DisjointSetForest groups = new DisjointSetForest(n, grades);
        for (int query = 0; query < m; query++) {
            String student1 = scanner.next();
            String student2 = scanner.next();
            int i = nameIndex.get(student1);
            int j = nameIndex.get(student2);

            if (groups.find(i) == groups.find(j)) {
                // Already in the same group.
                continue;
            } else if (groups.countSize(i) + groups.countSize(j) > b) {
                // Combined group would be larger than the maximum group size.
                continue;
            } else if (groups.countFirstGrade(i) + groups.countFirstGrade(j) > f) {
                // Combined group would have more than the maximum allowed first graders.
                continue;
            } else if (groups.countSecondGrade(i) + groups.countSecondGrade(j) > s) {
                // Combined group would have more than the maximum allowed second graders.
                continue;
            } else if (groups.countThirdGrade(i) + groups.countThirdGrade(j) > t) {
                // Combined group would have more than the maximum allowed third graders.
                continue;
            }

            groups.union(i, j);
        }

        final int maxSize = IntStream.range(0, n).map(i -> groups.countSize(i)).max().getAsInt();

        if (maxSize < a) {
            System.out.println("no groups");
            return;
        }

        List<String> members = nameIndex.entrySet()
                .stream()
                .filter(e -> groups.countSize(e.getValue()) == maxSize)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        for (String name : members) {
            System.out.println(name);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nmabfst = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nmabfst[0]);

        int m = Integer.parseInt(nmabfst[1]);

        int a = Integer.parseInt(nmabfst[2]);

        int b = Integer.parseInt(nmabfst[3]);

        int f = Integer.parseInt(nmabfst[4]);

        int s = Integer.parseInt(nmabfst[5]);

        int t = Integer.parseInt(nmabfst[6]);

        membersInTheLargestGroups(n, m, a, b, f, s, t);

        scanner.close();
    }
}

class DisjointSetForest {
    int[] parent;
    int[] size;
    int[][] gradeSizes;

    public DisjointSetForest(int n, int[] grades) {
        parent = new int[n];
        size = new int[n];
        gradeSizes = new int[n][3];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            gradeSizes[i][grades[i]]++;
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

        if (size[root1] > size[root2]) {
            parent[root2] = root1;
            size[parent[root2]] += size[root2];
            for (int grade = 0; grade < 3; grade++) {
                gradeSizes[parent[root2]][grade] += gradeSizes[root2][grade];
            }
        } else {
            parent[root1] = root2;
            size[parent[root1]] += size[root1];
            for (int grade = 0; grade < 3; grade++) {
                gradeSizes[parent[root1]][grade] += gradeSizes[root1][grade];
            }
        }
    }

    public int countSize(int i) {
        return size[find(i)];
    }

    public int countFirstGrade(int i) {
        return gradeSizes[find(i)][0];
    }

    public int countSecondGrade(int i) {
        return gradeSizes[find(i)][1];
    }

    public int countThirdGrade(int i) {
        return gradeSizes[find(i)][2];
    }
}
