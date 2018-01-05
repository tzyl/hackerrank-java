package com.tzyl.hackerrank.java.introduction.javaEndOfFile;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lineIndex = 0;
        while (sc.hasNext()) {
            lineIndex += 1;
            System.out.println(lineIndex + " " + sc.nextLine());
        }
    }
}
