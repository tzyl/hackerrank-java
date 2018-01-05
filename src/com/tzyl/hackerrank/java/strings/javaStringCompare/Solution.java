package com.tzyl.hackerrank.java.strings.javaStringCompare;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named UsernameValidator. */
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        String lexicographicMin = s.substring(0, k);
        String lexicographicMax = s.substring(0, k);
        for (int i = 1; i < s.length() + 1 - k; i++) {
            String currentSubstring = s.substring(i, i + k);
            if (currentSubstring.compareTo(lexicographicMin) < 0) {
                lexicographicMin = currentSubstring;
            }
            if (currentSubstring.compareTo(lexicographicMax) > 0) {
                lexicographicMax = currentSubstring;
            }
        }
        System.out.println(lexicographicMin);
        System.out.println(lexicographicMax);
    }
}
