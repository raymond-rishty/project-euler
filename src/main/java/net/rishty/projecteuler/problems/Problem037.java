package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import net.rishty.projecteuler.util.PrimeSieve;

/**
 * Truncatable primes
 * Problem 37
 * <p>
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left
 * to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379,
 * 37, and 3.
 * <p>
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 * <p>
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Problem037 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem037().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int sum = findSumOfTruncatablePrimes(1_000_000);
        System.out.println(sum);
    }

    int findSumOfTruncatablePrimes(int upperBound) {
        TIntList primes = PrimeSieve.getPrimesTInt(upperBound);
        int sum = 0;

        for (TIntIterator iterator = primes.iterator(); iterator.hasNext(); ) {
            int prime = iterator.next();
            if (isTruncatablePrime(prime, primes)) {
                sum += prime;
            }
        }

        return sum;
    }

    boolean isTruncatablePrime(int prime, TIntList primes) {
        return prime > 10 && isLeftTruncatable(prime, primes) && isRightTruncatable(prime, primes);
    }

    boolean isRightTruncatable(int prime, TIntList primes) {
        // 3797 -> 379 -> 37 -> 3
        for (int candidate = prime; candidate > 0; candidate /= 10) {
            if (primes.binarySearch(candidate) < 0) {
                return false;
            }
        }

        return true;
    }

    boolean isLeftTruncatable(int prime, TIntList primes) {
        // 3797 -> 797 -> 97 -> 7
        for (int t = 10; t < prime * 10; t *= 10) {
            if (primes.binarySearch(prime % t) < 0) {
                return false;
            }
        }

        return true;
    }
}
