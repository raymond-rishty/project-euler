package net.rishty.projecteuler.problems;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;
import static java.lang.Math.sqrt;

/**
 1000-digit Fibonacci number
 Problem 25
 The Fibonacci sequence is defined by the recurrence relation:

 Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 Hence the first 12 terms will be:

 F1 = 1
 F2 = 1
 F3 = 2
 F4 = 3
 F5 = 5
 F6 = 8
 F7 = 13
 F8 = 21
 F9 = 34
 F10 = 55
 F11 = 89
 F12 = 144
 The 12th term, F12, is the first term to contain three digits.

 What is the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Problem025 {
    public static void main(String args[]) {
        System.out.println(1 + (int) ceil(999d / log10((1d + sqrt(5)) / 2d)));
    }
}
