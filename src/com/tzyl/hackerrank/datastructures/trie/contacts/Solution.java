package com.tzyl.hackerrank.datastructures.trie.contacts;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named DuplicateWords. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String operation = sc.next();
            if (operation.equals("add")) {
                String name = sc.next();
                trie.insert(name);
            } else if (operation.equals("find")) {
                String partial = sc.next();
                System.out.println(trie.countPrefixWords(partial));
            }
        }
        sc.close();
    }
}

class Trie {
    private TrieNode root = new TrieNode();

    public int getSize() {
        return root.size;
    }

    public void insert(String word) {
        TrieNode current = root;
        root.size++;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.links[index] == null) {
                current.links[index] = new TrieNode();
            }
            current = current.links[index];
            current.size++;
        }
        current.isWord = true;
    }

    public int countPrefixWords(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.links[index] == null) {
                // Prefix does not exist in trie.
                return 0;
            }
            current = current.links[index];
        }
        return current.size;
    }

    public static class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        private int size = 0;
        private boolean isWord = false;
    }
}
