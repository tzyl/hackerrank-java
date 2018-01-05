package com.tzyl.hackerrank.datastructures.trie.noPrefixSet;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named DuplicateWords. */
        Scanner sc = new Scanner(System.in);

        Trie trie = new Trie();
        String badPrefix = null;

        int N = sc.nextInt();
        String[] prefixes = new String[N];
        for (int i = 0; i < N; i++) {
            prefixes[i] = sc.next();
        }
        for (int i = 0; i < N; i++) {
            trie.insert(prefixes[i]);
            if (!trie.checkValid(prefixes[i])) {
                badPrefix = prefixes[i];
                break;
            }
        }
        if (badPrefix != null) {
            System.out.println("BAD SET");
            System.out.println(badPrefix);
        } else {
            System.out.println("GOOD SET");
        }
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

    public boolean containsPrefix(String word) {
        boolean containsPrefix = false;
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.isWord) containsPrefix = true;
            current = current.links[index];
        }
        return containsPrefix;
    }

    public boolean checkValid(String word) {
        // Bad prefix if a previous word is a prefix of current word or
        // the current word is a prefix of one of the previous words.
        return !containsPrefix(word) && countPrefixWords(word) == 1;
    }


    public static class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        private int size = 0;
        private boolean isWord = false;
    }
}
