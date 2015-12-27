package net.rishty.projecteuler.problems;

import com.google.common.base.Objects;
import com.google.common.base.Stopwatch;
import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.stream.Stream;

/**
 Square root convergents
 Problem 57

 It is possible to show that the square root of two can be expressed as an infinite continued fraction.

 √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

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
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem057().run();
    System.out.println(stopwatch);
  }

  private void run() {
    int count = countGreaterNumerator(1000);
    System.out.println(count);
  }

  private int countGreaterNumerator(int limit) {
    return (int) Stream.iterate(new Fraction(BigInteger.ONE, BigInteger.ONE), Fraction::next)
            .limit(limit + 1)
            .filter(this::isDigitLengthDifferent)
            .count();
  }

  public boolean isDigitLengthDifferent(Fraction fraction) {
    return digitLength(fraction.numerator) > digitLength(fraction.denominator);
  }

  int digitLength(BigInteger n) {
    return BigIntegerMath.log10(n, RoundingMode.FLOOR) + 1;
  }

  static class Fraction {
    private final BigInteger numerator;
    private final BigInteger denominator;

    Fraction(BigInteger numerator, BigInteger denominator) {
      this.numerator = numerator;
      this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Fraction fraction = (Fraction) o;
      return Objects.equal(numerator, fraction.numerator) &&
              Objects.equal(denominator, fraction.denominator);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(numerator, denominator);
    }

    @Override
    public String toString() {
      return String.format("%s/%s", numerator, denominator);
    }

    Fraction next() {
      return new Fraction(numerator.add(denominator).add(denominator),
              numerator.add(denominator));
    }
  }
}
