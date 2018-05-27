package com.tzyl.hackerrank.worldcodesprint.wcs13.landslide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static TreeNode[] vertexNodes;
    private static LinkCutTree.Node[] linkCutTreeNodes;
    private static List<List<Integer>> adjacencyList;
    private static Set<UnorderedPair> edgeSet;

    private static ArrayList<Query> queries;
    private static Map<UnorderedPair, Integer> lcaOffline;

    /**
     * O(n + q log(n)) using link cut tree for dynamic connectivity and
     * Tarjan's offline lowest common ancestor for answering distance queries.
     *
     * Timeout on test case 12 and onwards.
     */
    public static void landslide(int n) throws IOException {
        initializeNodes(n);
        int q = Integer.parseInt(in.readLine());
        initializeQueries(q);

        for (int t = 0; t < q; t++) {
            String type = queries.get(t).type;
            UnorderedPair edge = queries.get(t).edge;
            int x = edge.x;
            int y = edge.y;
            if (type.equals("d")) {
                if (edgeSet.contains(edge) && LinkCutTree.connected(linkCutTreeNodes[x], linkCutTreeNodes[y])) {
                    LinkCutTree.cut(linkCutTreeNodes[x], linkCutTreeNodes[y]);
                }
            } else if (type.equals("c")) {
                if (edgeSet.contains(edge) && !LinkCutTree.connected(linkCutTreeNodes[x], linkCutTreeNodes[y])) {
                    LinkCutTree.link(linkCutTreeNodes[x], linkCutTreeNodes[y]);
                }
            } else if (type.equals("q")) {
                if (!LinkCutTree.connected(linkCutTreeNodes[x], linkCutTreeNodes[y])) {
                    System.out.println("Impossible");
                } else {
                    System.out.println(distance(x, y));
                }
            }
        }
    }

    private static void initializeNodes(int n) throws IOException {
        // Create nodes
        vertexNodes = new TreeNode[n];
        linkCutTreeNodes = new LinkCutTree.Node[n];
        for (int i = 0; i < n; i++) {
            vertexNodes[i] = new TreeNode(i);
            linkCutTreeNodes[i] = new LinkCutTree.Node(i);
        }

        // Build adjacency list.
        adjacencyList = new ArrayList<>(n);
        edgeSet = new HashSet<>(n);
        for (int vertex = 0; vertex < n; vertex++) {
            adjacencyList.add(new LinkedList<>());
        }
        for (int edgeIndex = 0; edgeIndex < n - 1; edgeIndex++) {
            // Change to 0 index for convenience of adjacency list.
            String[] tokens = in.readLine().split(" ");
            int u = Integer.parseInt(tokens[0]) - 1;
            int v = Integer.parseInt(tokens[1]) - 1;
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
            edgeSet.add(new UnorderedPair(u, v));
            LinkCutTree.link(linkCutTreeNodes[u], linkCutTreeNodes[v]);
        }
    }

    private static void initializeQueries(int q) throws IOException {
        queries = new ArrayList<>(q);
        int n = vertexNodes.length;
        List<Set<UnorderedPair>> lcaQueries = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            lcaQueries.add(new HashSet<>());
        }
        for (int t = 0; t < q; t++) {
            String[] tokens = in.readLine().split(" ");
            String type = tokens[0];
            int x = Integer.parseInt(tokens[1]) - 1;
            int y = Integer.parseInt(tokens[2]) - 1;

            Query query = new Query(type, x, y);
            queries.add(query);
            if (query.type.equals("q")) {
                lcaQueries.get(query.edge.x).add(query.edge);
                lcaQueries.get(query.edge.y).add(query.edge);
            }
        }

        lcaOffline = new HashMap<>(n);
        // DFS to build tree and pre-process LCA.
        // Root the tree at vertex 0 (arbitrary choice).
        buildTree(vertexNodes[0], lcaQueries);
    }

    private static void buildTree(TreeNode root, List<Set<UnorderedPair>> lcaQueries) {
        int n = vertexNodes.length;

        DisjointSetForest dsf = new DisjointSetForest(n);

        boolean[] seen = new boolean[n];
        seen[root.key] = true;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(root.key);

        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (x != -1) {
                // Push marker to know when finished node.
                stack.push(x);
                stack.push(-1);

                TreeNode u = vertexNodes[x];
                u.ancestor = u;
                for (int i : adjacencyList.get(u.key)) {
                    if (!seen[i]) {
                        seen[i] = true;
                        // Build tree
                        TreeNode v = vertexNodes[i];
                        v.depth = u.depth + 1;
                        v.parent = u;
                        stack.push(v.key);
                    }
                }
            } else {
                // Finished node
                int y = stack.pop();
                TreeNode u = vertexNodes[y];

                u.isBlack = true;
                for (UnorderedPair lcaQuery : lcaQueries.get(u.key)) {
                    TreeNode v;
                    if (lcaQuery.x == u.key) {
                        v = vertexNodes[lcaQuery.y];
                    } else {
                        v = vertexNodes[lcaQuery.x];
                    }
                    if (v.isBlack) {
                        lcaOffline.put(lcaQuery, vertexNodes[dsf.find(v.key)].ancestor.key);
                    }
                }

                if (u.parent != null) {
                    dsf.union(u.key, u.parent.key);
                    vertexNodes[dsf.find(u.parent.key)].ancestor = u.parent;
                }
            }
        }
    }

    private static int distance(int i, int j) {
        TreeNode u = vertexNodes[i];
        TreeNode v = vertexNodes[j];
        // Use LCA and depths to calculate distance.
        UnorderedPair lcaQuery = new UnorderedPair(u.key, v.key);
        TreeNode lca = vertexNodes[lcaOffline.get(lcaQuery)];
        return (u.depth - lca.depth) + (v.depth - lca.depth);
    }

    // private static final Scanner scanner = new Scanner(System.in);
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        landslide(n);

        in.close();
    }
}

class Query {
    public final String type;
    public final UnorderedPair edge;

    public Query(String type, int x, int y) {
        this.type = type;
        this.edge = new UnorderedPair(x, y);
    }
}

class UnorderedPair {
    public final int x;
    public final int y;

    public UnorderedPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals (final Object O) {
        if (!(O instanceof UnorderedPair)) return false;
        UnorderedPair other = (UnorderedPair) O;
        return ((x == other.x && y == other.y) || (x == other.y && y == other.x));
    }

    public int hashCode() {
        if (y < x) {
            return Objects.hash(y, x);
        }
        return Objects.hash(x, y);
    }
}

class TreeNode {
    public int key;
    public int depth;
    public TreeNode parent;
    public TreeNode ancestor;
    public boolean isBlack;

    public TreeNode(int key) {
        this.key = key;
    }
}

class DisjointSetForest {
    int[] parent;
    int[] size;

    public DisjointSetForest(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
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
        } else {
            parent[root1] = root2;
            size[parent[root1]] += size[root1];
        }
    }
}

/**
 * LinkCut tree with path queries. Query complexity is O(log(n)) amortized.
 * Based on Daniel Sleator's implementation http://www.codeforces.com/contest/117/submission/860934
 */
class LinkCutTree {

    static class Node {
        int nodeValue;
        boolean revert;
        Node left;
        Node right;
        Node parent;

        Node(int value) {
            nodeValue = value;
        }

        // tests whether x is a root of a splay tree
        boolean isRoot() {
            return parent == null || (parent.left != this && parent.right != this);
        }

        void push() {
            if (revert) {
                revert = false;
                Node t = left;
                left = right;
                right = t;
                if (left != null) {
                    left.revert = !left.revert;
                }
                if (right != null) {
                    right.revert = !right.revert;
                }
            }
        }
    }

    static void connect(Node ch, Node p, Boolean isLeftChild) {
        if (ch != null) {
            ch.parent = p;
        }
        if (isLeftChild != null) {
            if (isLeftChild) {
                p.left = ch;
            } else {
                p.right = ch;
            }
        }
    }

    // rotates edge (x, x.parent)
    //        g            g
    //       /            /
    //      p            x
    //     / \    ->    / \
    //    x  p.r      x.l  p
    //   / \              / \
    // x.l x.r          x.r p.r
    static void rotate(Node x) {
        Node p = x.parent;
        Node g = p.parent;
        boolean isRootP = p.isRoot();
        boolean leftChildX = (x == p.left);

        // create 3 edges: (x.r(l),p), (p,x), (x,g)
        connect(leftChildX ? x.right : x.left, p, leftChildX);
        connect(p, x, !leftChildX);
        connect(x, g, isRootP ? null : p == g.left);
    }

    // brings x to the root, balancing tree
    //
    // zig-zig case
    //        g                                  x
    //       / \               p                / \
    //      p  g.r rot(p)    /   \     rot(x) x.l  p
    //     / \      -->    x       g    -->       / \
    //    x  p.r          / \     / \           x.r  g
    //   / \            x.l x.r p.r g.r             / \
    // x.l x.r                                    p.r g.r
    //
    // zig-zag case
    //      g               g
    //     / \             / \               x
    //    p  g.r rot(x)   x  g.r rot(x)    /   \
    //   / \      -->    / \      -->    p       g
    // p.l  x           p  x.r          / \     / \
    //     / \         / \            p.l x.l x.r g.r
    //   x.l x.r     p.l x.l
    static void splay(Node x) {
        while (!x.isRoot()) {
            Node p = x.parent;
            Node g = p.parent;
            if (!p.isRoot()) {
                g.push();
            }
            p.push();
            x.push();
            if (!p.isRoot()) {
                rotate((x == p.left) == (p == g.left) ? p/*zig-zig*/ : x/*zig-zag*/);
            }
            rotate(x);
        }
        x.push();
    }

    // makes node x the root of the virtual tree, and also x becomes the leftmost node in its splay tree
    static Node expose(Node x) {
        Node last = null;
        for (Node y = x; y != null; y = y.parent) {
            splay(y);
            y.left = last;
            last = y;
        }
        splay(x);
        return last;
    }

    public static void makeRoot(Node x) {
        expose(x);
        x.revert = !x.revert;
    }

    public static boolean connected(Node x, Node y) {
        if (x == y) {
            return true;
        }
        expose(x);
        // now x.parent is null
        expose(y);
        return x.parent != null;
    }

    public static void link(Node x, Node y) {
        if (connected(x, y)) {
            throw new RuntimeException("error: x and y are already connected");
        }
        makeRoot(x);
        x.parent = y;
    }

    public static void cut(Node x, Node y) {
        makeRoot(x);
        expose(y);
        // check that exposed path consists of a single edge (y,x)
        if (y.right != x || x.left != null) {
            throw new RuntimeException("error: no edge (x,y)");
        }
        y.right.parent = null;
        y.right = null;
    }
}
