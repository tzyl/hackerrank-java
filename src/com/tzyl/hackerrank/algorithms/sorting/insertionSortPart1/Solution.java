package com.tzyl.hackerrank.algorithms.sorting.insertionSortPart1;

import java.util.*;

public class Solution {

    public static void insertIntoSorted(int[] ar) {
        int i = ar.length - 1;
        int e = ar[ar.length - 1];
        while (i > 0 && ar[i - 1] > e) {
            ar[i] = ar[i - 1];
            printArray(ar);
            i--;
        }
        ar[i] = e;
        printArray(ar);
    }

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        insertIntoSorted(ar);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }


}
