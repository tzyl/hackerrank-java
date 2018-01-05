package com.tzyl.hackerrank.algorithms.strings.camelCase;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int words = 1;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                words++;
            }
        }
        System.out.println(words);
    }
}
