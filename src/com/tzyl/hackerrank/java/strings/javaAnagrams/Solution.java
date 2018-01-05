package com.tzyl.hackerrank.java.strings.javaAnagrams;

import java.util.HashMap;

/**
 * Created by Tim on 13/03/2017.
 */
public class Solution {
    static boolean isAnagram(String a, String b) {
        // Complete the function by writing your code here.
        if (a.length() != b.length()) {
            return false;
        }
        HashMap<Character, Integer> seen = new HashMap();
        for (int i = 0; i < a.length(); i++) {
            Character c = Character.toLowerCase(a.charAt(i));
            int occurences = seen.getOrDefault(c, 0) + 1;
            seen.put(c, occurences);
        }
        for (int i = 0; i < b.length(); i++) {
            Character c = Character.toLowerCase(b.charAt(i));
            if (!seen.containsKey(c) || seen.get(c) == 0) {
                return false;
            }
            int occurences = seen.get(c) - 1;
            seen.put(c, occurences);
        }
        return true;
    }
}
