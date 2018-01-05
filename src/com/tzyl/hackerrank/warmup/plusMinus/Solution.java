package com.tzyl.hackerrank.warmup.plusMinus;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        float positive = 0;
        float negative = 0;
        float zeros = 0;
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            if (a > 0) positive++;
            else if (a < 0) negative++;
            else zeros++;
        }
        System.out.println(positive / N);
        System.out.println(negative / N);
        System.out.println(zeros / N);
    }
}
