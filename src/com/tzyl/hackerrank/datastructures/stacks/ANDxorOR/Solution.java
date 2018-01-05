package com.tzyl.hackerrank.datastructures.stacks.ANDxorOR;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        int best = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()) {
                best = Math.max(best, andXorOr(A[i], stack.peek()));
                if (A[i] < stack.peek()) {
                    // No more use for this element on the stack. It can no
                    // longer form a minimum pair with elements to the right.
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(A[i]);
        }
        System.out.println(best);
    }

    public static int andXorOr(int M1, int M2) {
        return (((M1 & M2) ^ (M1 | M2)) & (M1 ^ M2));
    }
}
