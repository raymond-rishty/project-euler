package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
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
    TIntList primeList = PrimeSieve.getPrimesTInt(upperLimit);
    TIntIntMap map = new TIntIntHashMap();

    int totalSum = 0;

    for (TIntIterator primeIterator = primeList.iterator(); primeIterator.hasNext() && totalSum < upperLimit; ) {
      int prime = primeIterator.next();
      totalSum += prime;
      map.put(prime, totalSum);
    }

    for (int length = map.size() - 1; length > 0; length--) {
      for (int index = 0; index <= map.size() - length; index++) {
        int lowerSum = index == 0 ? 0 : map.get(primeList.get(index));
        int upperSum = map.get(primeList.get(index + length));
        int sum = upperSum - lowerSum;
        if (primeList.binarySearch(sum) > -1) {
          return sum;
        }
      }
    }

    return 0;
  }
}
