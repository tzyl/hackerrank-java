package com.tzyl.hackerrank.worldcodesprint.wcs13.balancedSequence;

import java.io.*;
import java.util.*;

public class Solution {

    /**
     * O(n) using stack to remove all substrings which are valid parentheses.
     */
    public static int fewestOperationsToBalance(String s) {
        // We can remove any valid parentheses as applying an operation to the
        // whole of a valid parentheses group will keep it valid.
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else if (stack.isEmpty() || stack.peek() != '(') {
                stack.push(')');
            } else {
                // Found matching parenthesis
                stack.pop();
            }
        }
        // After removing these we cannot have "()" so the remaining string
        // must be a sequence of ")" followed by a sequence of "(".
        int open = 0;
        int close = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c == '(') {
                open++;
            } else {
                close++;
            }
        }
        // We either need 0, 1, or 2 operations to fix these:
        // If there are no characters then already balanced so 0 operations.
        // If there is only a sequence of ")" or only a sequence of "(" we
        // need 1 operation to flip half of them.
        // If there is a sequence of ")" and a sequence of "(" we need 2
        // operations to flip half of the ")" and half of the "(".
        int operations = 0;
        if (open > 0) {
            operations++;
        }
        if (close > 0) {
            operations++;
        }
        return operations;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = fewestOperationsToBalance(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
