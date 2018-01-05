package com.tzyl.hackerrank.java.strings.javaStringReverse;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        /* Enter your code here. Print output to STDOUT. */
        boolean isPalindrome = Solution.isPalindrome(s);
        System.out.println(isPalindrome ? "Yes" : "No");
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
