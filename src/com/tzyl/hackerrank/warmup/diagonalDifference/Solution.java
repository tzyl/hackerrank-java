package com.tzyl.hackerrank.warmup.diagonalDifference;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();
            }
        }
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;
        for (int i = 0; i < N; i++) {
            primaryDiagonal += A[i][i];
            secondaryDiagonal += A[i][N - 1 - i];
        }
        System.out.println(Math.abs(primaryDiagonal - secondaryDiagonal));
    }
}
