package com.tzyl.hackerrank.weekofcode.w33.palindromicTable;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int[][][] rectangleCache;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        int[][] table = new int[n][m];
        for (int table_i = 0; table_i < n; table_i++) {
            for (int table_j = 0; table_j < m; table_j++) {
                table[table_i][table_j] = in.nextInt();
            }
        }

        // rectangleCache[i][j] contains an array of digit counts for the rectangle
        //  with top left corner (i, j) and bottom right corner (n - 1, m - 1).
        rectangleCache = new int[n][m][10];
        // Fill in rectangleCache O(m * n^2). If m < n we swap roles so O(n * m^2).
        if (m < n) {
            for (int width = 1; width <= m; width++) {
                int[] digitCount = new int[10];
                for (int height = 1; height <= n; height++) {
                    int i = n - height;
                    int j = m - width;
                    for (int y = j; y < m; y++) {
                        digitCount[table[i][y]]++;
                    }
                    for (int digit = 0; digit < 10; digit++) {
                        rectangleCache[i][j][digit] = digitCount[digit];
                    }
                }
            }
        } else {
            for (int height = 1; height <= n; height++) {
                int[] digitCount = new int[10];
                for (int width = 1; width <= m; width++) {
                    int i = n - height;
                    int j = m - width;
                    for (int x = i; x < n; x++) {
                        digitCount[table[x][j]]++;
                    }
                    for (int digit = 0; digit < 10; digit++) {
                        rectangleCache[i][j][digit] = digitCount[digit];
                    }
                }
            }
        }

        int bestArea = 0;
        int x1, y1, x2, y2;
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;

        for (int height = 1; height <= n; height++) {
            for (int width = 1; width <= m; width++) {
                if (height * width <= bestArea) continue;
                for (int i = 0; i <= n - height; i++) {
                    if (height * width <= bestArea) break;
                    for (int j = 0; j <= m - width; j++) {
                        if (height * width <= bestArea) break;
                        if (isBeautifulRectangle(i, j, height, width)) {
                            bestArea = height * width;
                            x1 = i;
                            y1 = j;
                            x2 = i + height - 1;
                            y2 = j + width - 1;
                        }
                    }
                }
            }
        }

        System.out.println(bestArea);
        System.out.printf("%d %d %d %d", x1, y1, x2, y2);
    }

    public static boolean isBeautifulRectangle(int i, int j, int height, int width) {
        if (height == 1 && width == 1) return true;
        int[] digitCount = calculateRectangleDigitCount(i, j, height, width);
        int oddCount = 0;
        int nonZeroCount = 0;
        for (int x = 0; x < 10; x++) {
            oddCount += digitCount[x] % 2;
            if (x != 0) nonZeroCount += digitCount[x];
        }
        return (oddCount <= 1 && nonZeroCount >= 2);
    }

    public static int[] calculateRectangleDigitCount(int i, int j, int height, int width) {
        int[] A = rectangleCache[i][j];
        int[] B;
        int[] C;
        int[] D;
        int[] digitCount = new int[10];

        if (i + height < n && j + width < m) {
            B = rectangleCache[i + height][j];
            C = rectangleCache[i][j + width];
            D = rectangleCache[i + height][j + width];
        } else if (i + height < n) {
            B = rectangleCache[i + height][j];
            C = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            D = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        } else if (j + width < m) {
            B = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            C = rectangleCache[i][j + width];
            D = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        } else {
            B = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            C = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            D = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        for (int x = 0; x < 10; x++) {
            digitCount[x] = A[x] - B[x] - C[x] + D[x];
        }

        return digitCount;
    }
}
