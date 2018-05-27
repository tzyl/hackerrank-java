package com.tzyl.hackerrank.worldcodesprint.wcs13.findTheAbsentStudents;

import java.io.*;
import java.util.*;

public class Solution {

    public static int[] findTheAbsentStudents(int n, int[] a) {
        List<Integer> absentStudents = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            boolean absent = true;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    absent = false;
                    break;
                }
            }
            if (absent) {
                absentStudents.add(i);
            }
        }
        return absentStudents.stream().mapToInt(i -> i).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = findTheAbsentStudents(n, a);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
