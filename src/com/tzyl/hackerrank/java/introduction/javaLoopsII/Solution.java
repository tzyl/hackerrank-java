package com.tzyl.hackerrank.java.introduction.javaLoopsII;

import java.util.*;
import java.io.*;

class Solution{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            int x = a;
            for (int j = 0; j < n; j++) {
                x = 2 * ((x - a) + b) - b + a;
                if (j > 0) System.out.print(" ");
                System.out.print(x);
            }
            System.out.println();

        }
        in.close();
    }
}
