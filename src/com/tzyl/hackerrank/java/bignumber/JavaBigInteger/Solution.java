package com.tzyl.hackerrank.java.bignumber.JavaBigInteger;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger x = new BigInteger(sc.next());
        BigInteger y = new BigInteger(sc.next());
        System.out.println(x.add(y));
        System.out.println(x.multiply(y));
    }
}
