package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 Permuted multiples
 Problem 52
 It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

 Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class Problem052 {
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem052().run();
    System.out.println(stopwatch);
  }

  public void run() {
    OptionalInt numberWithMultiplePermutations = findNumberWithMultiplePermutations(6);
    /*OptionalInt numberWithMultiplePermutations = findNumbersWithMultiplePermutationsBounded(6, 123456, 1654321)
      .findFirst();*/
    System.out.println(numberWithMultiplePermutations.getAsInt());
  }
  OptionalInt findNumberWithMultiplePermutations(int highestMultiple) {
    return IntStream.iterate(2, num -> num + 1)
      .flatMap(num -> findNumberWithMultiplePermutations(num, highestMultiple))
      .findFirst();

  }
  IntStream findNumberWithMultiplePermutations(int size, int highestMultiple) {
    int lowerBound = IntMath.pow(10, size - 2);
    int upperBound = IntMath.pow(10, size - 1) / highestMultiple;
    return findNumbersWithMultiplePermutationsBounded(highestMultiple, lowerBound, upperBound);
  }

  private IntStream findNumbersWithMultiplePermutationsBounded(int highestMultiple, int lowerBound, int upperBound) {
    return IntStream.range(lowerBound, upperBound)
      .filter(num -> areMultiplesPermutations(num, highestMultiple));
  }

  boolean areMultiplesPermutations(int num, int highestMultiple) {
    return IntStream.rangeClosed(2, highestMultiple)
      .allMatch(multiplier -> Arrays.equals(getDigits(num), getDigits(num * multiplier)));
  }

  private int[] getDigits(int number) {
    int[] digits = new int[10];
    int r = number;
    while (r > 0) {
      int digit = r % 10;
      r /= 10;
      digits[digit]++;
    }

    return digits;
  }
}
