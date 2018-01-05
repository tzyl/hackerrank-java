package com.tzyl.hackerrank.datastructures.stacks.equalStacks;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        int h1[] = new int[n1];
        int h1Height = 0;
        for(int h1_i=0; h1_i < n1; h1_i++){
            h1[h1_i] = in.nextInt();
            h1Height += h1[h1_i];
        }
        int h2[] = new int[n2];
        int h2Height = 0;
        for(int h2_i=0; h2_i < n2; h2_i++){
            h2[h2_i] = in.nextInt();
            h2Height += h2[h2_i];
        }
        int h3[] = new int[n3];
        int h3Height = 0;
        for(int h3_i=0; h3_i < n3; h3_i++){
            h3[h3_i] = in.nextInt();
            h3Height += h3[h3_i];
        }
        int h1Index = 0;
        int h2Index = 0;
        int h3Index = 0;
        while (h1Height != h2Height || h1Height != h3Height) {
            if (h1Height >= h2Height && h1Height >= h3Height) {
                h1Height -= h1[h1Index];
                h1Index++;
            } else if (h2Height >= h1Height && h2Height >= h3Height) {
                h2Height -= h2[h2Index];
                h2Index++;
            } else {
                h3Height -= h3[h3Index];
                h3Index++;
            }
        }
        System.out.println(h1Height);
    }
}
