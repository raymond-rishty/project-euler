package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;

import static com.google.common.math.BigIntegerMath.factorial;

/**
 Factorial digit sum
 Problem 20

 n! means n × (n − 1) × ... × 3 × 2 × 1

 For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

 Find the sum of the digits in the number 100!
 */
public class Problem020 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem020().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        BigInteger factorial = factorial(100);
        int digitSum = digitSum(factorial);
        System.out.println(digitSum);
    }

    private int digitSum(BigInteger factorial) {
        int sum = 0;
        BigInteger left = factorial;
        while (left.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divAndRemainder = left.divideAndRemainder(BigInteger.TEN);
            left = divAndRemainder[0];
            sum += divAndRemainder[1].intValue();
        }

        return sum;
    }
}
