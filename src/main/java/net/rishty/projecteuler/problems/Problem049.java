package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import net.rishty.projecteuler.util.PrimeSieve;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 Prime permutations
 Problem 49

 The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
    (i) each of the three terms are prime, and,
    (ii) each of the 4-digit numbers are permutations of one another.

 There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

 What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class Problem049 {
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem049().run();
    System.out.println(stopwatch.stop());
  }

  public void run() {
    List<String> increasingPrimePermutations = findIncreasingPrimePermutations();
    String answer = increasingPrimePermutations.stream()
      .filter(number -> !number.startsWith("1487"))
      .findAny()
      .get();
    System.out.println(answer);
  }

  List<String> findIncreasingPrimePermutations() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    LinkedHashSet<Integer> primes = new LinkedHashSet<>(PrimeSieve.getPrimes(10000)
      .stream()
      .filter(prime -> prime > 1000)
      .collect(toList()));
    System.out.printf("%s to find the primes %n", stopwatch);

    int increment = 3330;

    stopwatch = Stopwatch.createStarted();
    List<String> collect = primes
      .stream()
      .filter(prime -> prime < 3310)
      .filter(prime -> primes.contains(prime + increment))
      .filter(prime -> permutes(prime, prime + increment))
      .filter(prime -> primes.contains(prime + 2 * increment))
      .filter(prime -> permutes(prime, prime + 2 * increment))
      .map(prime -> String.format("%s%s%s", prime, prime + increment, prime + 2 * increment))
      .collect(Collectors.toList());
    System.out.printf("%s to find the increasing prime permutations%n", stopwatch);

    return collect;
  }

  private boolean permutes(int prime, int anotherPrime) {
    return getDigits(prime).equals(getDigits(anotherPrime));
  }

  private HashMultiset<Integer> getDigits(int prime) {
    HashMultiset<Integer> digits = HashMultiset.create(4);
    int r = prime;
    while (r > 0) {
      int digit = r % 10;
      r /= 10;
      digits.add(digit);
    }
    return digits;
  }
}
