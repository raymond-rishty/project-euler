package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSortedSet;
import net.rishty.projecteuler.util.PrimeSieve;

/**
 Consecutive prime sum
 Problem 50
 The prime 41, can be written as the sum of six consecutive primes:

 41 = 2 + 3 + 5 + 7 + 11 + 13
 This is the longest sum of consecutive primes that adds to a prime below one-hundred.

 The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

 Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class Problem050 {
  public static void main(String[] args) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    new Problem050().run();
    System.out.println(stopwatch.stop());
  }

  public void run() {
    int sumOfConsecutivePrimes = getSumOfConsecutivePrimes(1_000_000);
    System.out.println(sumOfConsecutivePrimes);
  }

  public int getSumOfConsecutivePrimes(int upperLimit) {
    ImmutableSortedSet<Integer> primes = ImmutableSortedSet.copyOf(PrimeSieve.getPrimes(upperLimit));

    int maxLength = 0;
    int bestSum = 0;

    for (int index = 0; index < primes.size(); index++) {
      for (int length = maxLength + 1; length < primes.size() - index; length++) {
        int sum = getSum(primes, index, length);
        if (sum > upperLimit) {
          break;
        }
        if (primes.contains(sum)) {
          maxLength = length;
          bestSum = sum;
        }
      }
    }

    return bestSum;
  }

  private int getSum(ImmutableSortedSet<Integer> primeList, int index, int length) {
    return primeList.subSet(index, index + length)
      .stream()
      .mapToInt(Integer::intValue)
      .sum();
  }
}
