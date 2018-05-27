package com.tzyl.hackerrank.worldcodesprint.wcs13.competitiveTeams;

import java.util.*;

public class Solution {

    /**
     * O(q n^1/2) to answer q queries with n developers.
     */
    public static void competitiveTeams(int n, int q) {
        DisjointSetForest teams = new DisjointSetForest(n);
        for (int t = 0; t < q; t++) {
            int type = scanner.nextInt();
            if (type == 1) {
                // Zero index for convenience
                int developer1 = scanner.nextInt() - 1;
                int developer2 = scanner.nextInt() - 1;
                teams.union(developer1, developer2);
            } else if (type == 2) {
                int c = scanner.nextInt();
                SortedMap<Integer, Integer> sizeCount = teams.getSizeCount();
                System.out.println(calculateCompetitiveTeams(teams, c));
            }
        }
    }

    /**
     * Returns the number of pairs of teams which are competitive, i.e. the
     * difference in the sizes of the teams is >= c.
     *
     * O(n^1/2) as there can be at most n^1/2 distinct team sizes.
     */
    private static long calculateCompetitiveTeams(DisjointSetForest teams, int c) {
        List<Map.Entry<Integer, Integer>> sizeCount = new ArrayList<>(teams.getSizeCount().entrySet());
        if (c == 0) {
            long totalTeams = sizeCount.stream().map(Map.Entry::getValue).mapToInt(i -> i).sum();
            return totalTeams * (totalTeams - 1) / 2;
        }
        long total = 0;
        long cumulativeLower = 0;
        int i = 0;
        for (int j = 0; j < sizeCount.size(); j++) {
            int size = sizeCount.get(j).getKey();
            long count = sizeCount.get(j).getValue();
            while (i <= j && size - sizeCount.get(i).getKey() >= c) {
                cumulativeLower += sizeCount.get(i).getValue();
                i++;
            }
            total += count * cumulativeLower;
        }
        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0]);

        int q = Integer.parseInt(nq[1]);

        competitiveTeams(n, q);

        scanner.close();
    }
}

class DisjointSetForest {
    int[] parent;
    int[] size;
    SortedMap<Integer, Integer> sizeCount;

    public DisjointSetForest(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        sizeCount = new TreeMap<>();
        sizeCount.put(1, n);
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

        decrementSizeCount(size[root1]);
        decrementSizeCount(size[root2]);
        incrementSizeCount(size[root1] + size[root2]);
        if (size[root1] > size[root2]) {
            parent[root2] = root1;
            size[parent[root2]] += size[root2];
        } else {
            parent[root1] = root2;
            size[parent[root1]] += size[root1];
        }
    }

    public SortedMap<Integer, Integer> getSizeCount() {
        return sizeCount;
    }

    private void incrementSizeCount(int size) {
        sizeCount.put(size, sizeCount.getOrDefault(size, 0) + 1);
    }

    private void decrementSizeCount(int size) {
        int newCount = sizeCount.getOrDefault(size, 0) - 1;
        if (newCount > 0) {
            sizeCount.put(size, newCount);
        } else if (newCount == 0) {
            sizeCount.remove(size);
        }
    }
}
