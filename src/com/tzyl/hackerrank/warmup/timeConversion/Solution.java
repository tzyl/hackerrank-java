package com.tzyl.hackerrank.warmup.timeConversion;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String time = sc.next();
        String hour = time.substring(0, 2);
        String minute = time.substring(3, 5);
        String second = time.substring(6, 8);
        String mode = time.substring(time.length() - 2);
        if (mode.equals("AM") && hour.equals("12")) {
            hour = "00";
        } else if (mode.equals("PM") && !hour.equals("12")) {
            hour = String.valueOf(Integer.parseInt(hour) + 12);
        }
        System.out.printf("%s:%s:%s", hour, minute, second);
    }
}
