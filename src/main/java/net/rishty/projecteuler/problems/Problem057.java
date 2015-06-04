package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;

/**
 Square root convergents
 Problem 57
 It is possible to show that the square root of two can be expressed as an infinite continued fraction.

 ? 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

 By expanding this for the first four iterations, we get:

 1 + 1/2 = 3/2 = 1.5
 1 + 1/(2 + 1/2) = 7/5 = 1.4
 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

 The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example
 where the number of digits in the numerator exceeds the number of digits in the denominator.

 In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
 */
public class Problem057 {
  public static void main(String args[]) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem057().run();
    System.out.println(stopwatch);
  }

  private void run() {
    int count = countGreaterNumerator(1_000);
    System.out.println(count);
  }

  private int countGreaterNumerator(int maxExpansion) {
    BigInteger pn2 = BigInteger.valueOf(0);
    BigInteger pn1 = BigInteger.valueOf(1);
    BigInteger hn2 = BigInteger.valueOf(2);
    BigInteger hn1 = BigInteger.valueOf(2);

    int count = 0;

    for (int i = 0; i < maxExpansion; i++) {
      BigInteger pn = pn1.add(pn1).add(pn2);
      pn2 = pn1;
      pn1 = pn;

      BigInteger c = hn1.add(hn1).add(hn2);
      hn2 = hn1;
      hn1 = c;

      BigInteger h = c.divide(BigInteger.valueOf(2));
      int hDigits = digitCount(h);
      int pDigits = digitCount(pn);
      if (hDigits > pDigits) {
        count++;
      }
    }

    return count;
  }

  private int digitCount(BigInteger number) {
    return number.toString().length();
  }
}
