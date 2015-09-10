package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.math.IntMath;

/**
 * Digit factorials
 * Problem 34
 * <p>
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * <p>
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * <p>
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class Problem034 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem034().run();
        System.out.println(stopwatch);
    }

    private void run() {
        long sum = findSumOfAllNumbersEqualToSumOfFactorialOfDigits(100000);
        System.out.println(sum);
    }

    private long findSumOfAllNumbersEqualToSumOfFactorialOfDigits(int upperLimit) {
        long sum = 0;
        for (int i = 10; i < upperLimit; i++) {
            if (isEqualToSumOfDigitFactorial(i)) {
                sum += i;
            }
        }

        return sum;
    }

    private boolean isEqualToSumOfDigitFactorial(int i) {
        return i == sumOfDigitFactorial(i);
    }

    private int sumOfDigitFactorial(int i) {
        int number = i;

        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            number = number / 10;
            sum += IntMath.factorial(digit);
        }

        return sum;
    }
}
