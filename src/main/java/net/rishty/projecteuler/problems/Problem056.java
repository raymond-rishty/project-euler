package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;

/**
 Powerful digit sum
 Problem 56
 A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

 Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
 */
public class Problem056 {
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem056().run();
    System.out.println(stopwatch);
  }

  private void run() {
    int digitSum = findMaximumDigitSumOfPower(100, 100);
    System.out.println(digitSum);
  }

  private int findMaximumDigitSumOfPower(int upperBoundBase, int upperBoundExponent) {
    int maxDigitSum = 0;

    for (int a = 1; a < upperBoundBase; a++) {
      for (int b = 1; b < upperBoundExponent; b++) {
        BigInteger power = BigInteger.valueOf(a).pow(b);
        int digitSum = digitSum(power);
        if (digitSum > maxDigitSum) {
          maxDigitSum = digitSum;
        }
      }
    }

    return maxDigitSum;
  }

  private int digitSum(BigInteger power) {
    int sum = 0;
    String powerStr = power.toString();

    for (char ch : powerStr.toCharArray()) {
      int digit = Character.digit(ch, 10);
      sum += digit;
    }

    return sum;
  }
}
