package com.tzyl.hackerrank.algorithms.graphtheory;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class roadsAndLibraries {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int libraryCost = in.nextInt();
            int roadCost = in.nextInt();
            // Initialize nodes.
            Node[] nodes = new Node[n];
            for (int i = 1; i <= n; i++) {
                nodes[i - 1] = new Node(i);
            }
            // Create edges using adjacency lists.
            for (int a1 = 0; a1 < m; a1++) {
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();
                nodes[city_1 - 1].addNeighbour(nodes[city_2 - 1]);
                nodes[city_2 - 1].addNeighbour(nodes[city_1 - 1]);
            }
            int numberOfComponents = calculateNumberOfComponents(nodes);
            long repairCost;
            if (libraryCost < roadCost) {
                repairCost = (long) n * libraryCost;
            } else {
                // A component with x cities can be connected with x - 1 roads.
                repairCost = (long) numberOfComponents * libraryCost + (long) (n - numberOfComponents) * roadCost;
            }
            System.out.println(repairCost);
        }
    }

    public static int calculateNumberOfComponents(Node[] nodes) {
        // DFS to find number of components.
        int numberOfComponents = 0;
        int n = nodes.length;
        HashSet<Integer> seenNodes = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (seenNodes.contains(i)) {
                continue;
            }
            numberOfComponents++;
            Deque<Integer> toVisit = new ArrayDeque<>();
            toVisit.push(i);
            while (!toVisit.isEmpty()) {
                Node node = nodes[toVisit.pop() - 1];
                for (Node neighbour : node.neighbours) {
                    int key = neighbour.key;
                    if (!seenNodes.contains(key)) {
                        seenNodes.add(key);
                        toVisit.push(key);
                    }
                }
            }
        }
        return numberOfComponents;
    }

    public static class Node {
        List<Node> neighbours = new ArrayList<>();
        int key;

        public Node(int key) {
            this.key = key;
        }

        public void addNeighbour(Node neighbour) {
            neighbours.add(neighbour);
        }
    }
}
