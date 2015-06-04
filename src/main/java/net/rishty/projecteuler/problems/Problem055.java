package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 Lychrel numbers
 Problem 55
 If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

 Not all numbers produce palindromes so quickly. For example,

 349 + 943 = 1292,
 1292 + 2921 = 4213
 4213 + 3124 = 7337

 That is, 349 took three iterations to arrive at a palindrome.

 Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that
 never forms a palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature
 of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise.
 In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than
 fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a
 palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a
 palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

 Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

 How many Lychrel numbers are there below ten-thousand?

 NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.
 */
public class Problem055 {
  public static void main(String[ ] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem055().run();
    System.out.println(stopwatch);
  }

  private void run() {
    int count = countLychrelNumbers(10_000);
    System.out.println(count);
  }

  int countLychrelNumbers(int upperBound) {
    return (int) IntStream.rangeClosed(10, upperBound)
      .filter(this::isLychrel)
      .count();
  }

  BigInteger reverse(BigInteger number) {
    BigInteger reversed = BigInteger.ZERO;
    while (number.signum() > 0) {
      BigInteger[] divideAndRemainder = number.divideAndRemainder(BigInteger.TEN);
      reversed = reversed.multiply(BigInteger.TEN).add(divideAndRemainder[1]);
      number = divideAndRemainder[0];
    }

    return reversed;
  }

  boolean isLychrel(int number) {
    int iterations = 0;
    BigInteger sum = BigInteger.valueOf(number);
    BigInteger reverse = reverse(sum);
    do {
      sum = sum.add(reverse);
      iterations++;
      reverse = reverse(sum);
      if (Objects.equals(sum, reverse)) {
        return false;
      }
    } while (iterations < 50);

    return true;
  }

}
