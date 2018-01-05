package com.tzyl.hackerrank.warmup.staircase;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("#");
            }
            System.out.println();
            // String stair = "";
            // for (int j = 0; j < i; j++) stair += "#";
            // System.out.printf("%" + N + "s%n", stair);
        }
    }
}
