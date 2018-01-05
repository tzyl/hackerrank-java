package com.tzyl.hackerrank.java.bignumber.JavaBigDecimal;

import java.math.BigDecimal;
import java.util.*;
class Solution{

    public static void main(String []args){
        // Input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++){
            s[i] = sc.next();
        }
        sc.close();

        // Write your code here
        Arrays.sort(s, 0, n, (s1, s2) -> {
            BigDecimal x = new BigDecimal(s1);
            BigDecimal y = new BigDecimal(s2);
            return y.compareTo(x);
        });

        // Output
        for (int i = 0; i < n; i++)
        {
            System.out.println(s[i]);
        }
    }

}