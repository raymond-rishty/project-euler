package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import net.rishty.projecteuler.util.PrimeSieve;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/*
Prime digit replacements
Problem 51
By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.

Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
 */
public class Problem051 {
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem051().run();
    System.out.println(stopwatch);
  }

  void run() {
    int expectedSize = 8;
    int[] firstFamily = getPrimeFamily(expectedSize);

    System.out.println(firstFamily[0]);
  }

  private int[] getPrimeFamily(int expectedSize) {
    TIntSet primes = new TIntHashSet(PrimeSieve.getPrimesTInt(1000000));

    List<int[]> numbersWithBlanksBetween = getNumbersWithBlanksBetween(10, 1000);
    return (int[]) numbersWithBlanksBetween
      .stream()
      .map(this::fillBlanks)
      .map(numberWithBlanks -> IntStream.of(numberWithBlanks).filter(primes::contains).toArray())
      .filter(family -> family.length == expectedSize)
      .findFirst()
      .get();
  }

  List<int[]> getNumbersWithBlanksBetween(int lowerLimit, int upperLimit) {
    return IntStream.range(lowerLimit, upperLimit)
      .mapToObj(Integer::valueOf)
      .flatMap(digitsAsNumber -> getNumbersWithBlanks(digitsAsNumber).stream())
      .collect(toList());
  }

  int[] fillBlanks(int[] numberWithBlanks) {
    return IntStream.rangeClosed(0, 9)
      .filter(repeatingDigit -> numberWithBlanks[0] != -1 || repeatingDigit != 0)
      .map(digit -> fillBlanks(numberWithBlanks, digit))
      .toArray();
  }

  int fillBlanks(int[] numberWithBlanks, int digit) {
    int number = 0;
    for (int digitOfPattern : numberWithBlanks) {
      number *= 10;
      if (digitOfPattern == -1) {
        number += digit;
      } else {
        number += digitOfPattern;
      }
    }

    return number;
  }

  List<int[]> getNumbersWithBlanks(int digitsAsNumber) {
    if (digitsAsNumber % 2 == 0 || digitsAsNumber % 5 == 0) {
      return ImmutableList.of();
    }

    int[] digits = getDigits(digitsAsNumber);

    List<int[]> list = new ArrayList<>();
    if (digitsAsNumber < 100) {
      list.add(new int[]{digits[0], -1, -1, -1, digits[1]});
      list.add(new int[]{-1, digits[0], -1, -1, digits[1]});
      list.add(new int[]{-1, -1, digits[0], -1, digits[1]});
      list.add(new int[]{-1, -1, -1, digits[0], digits[1]});
    } else {
      list.add(new int[]{digits[2], digits[1], -1, -1, -1, digits[0]});
      list.add(new int[]{digits[2], -1, digits[1], -1, -1, digits[0]});
      list.add(new int[]{digits[2], -1, -1, digits[1], -1, digits[0]});
      list.add(new int[]{digits[2], -1, -1, -1, digits[1], digits[0]});


      list.add(new int[]{-1, digits[2], digits[1], -1, -1, digits[0]});
      list.add(new int[]{-1, digits[2], -1, digits[1], -1, digits[0]});
      list.add(new int[]{-1, digits[2], -1, -1, digits[1], digits[0]});

      list.add(new int[]{-1, -1, digits[2], digits[1], -1, digits[0]});
      list.add(new int[]{-1, -1, digits[2], -1, digits[1], digits[0]});

      list.add(new int[]{-1, -1, -1, digits[2], digits[1], digits[0]});
    }

    return list;
  }

  private int[] getDigits(int digitsAsNumber) {
    int[] digits = new int[digitsAsNumber < 100 ? 2 : 3];
    int r = digitsAsNumber;
    int index = 0;
    while (r > 0) {
      digits[index++] = r % 10;
      r /= 10;
    }

    return digits;
  }
}
