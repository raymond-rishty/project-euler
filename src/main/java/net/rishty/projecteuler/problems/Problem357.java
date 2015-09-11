package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import gnu.trove.list.TIntList;
import net.rishty.projecteuler.util.PrimeSieve;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 Prime generating integers
 Problem 357

 Consider the divisors of 30: 1,2,3,5,6,10,15,30.
 It can be seen that for every divisor d of 30, d+30/d is prime.

 Find the sum of all positive integers n not exceeding 100 000 000
 such that for every divisor d of n, d+n/d is prime.
 */
public class Problem357 {
    public static void main(String[] args) {
        int upperBound = 100_000_000;
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem357().run(upperBound);
        System.out.printf("%sms%n", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    private void run(int upperBound) {
        TIntList primes = PrimeSieve.getPrimesTInt(upperBound);

        long sum = IntStream.of(primes.toArray())
                .parallel()
                .filter(prime -> prime % 4 == 3)
                .filter(prime -> checkPrimeGenerator(prime - 1, primes))
                .mapToLong(prime -> prime - 1)
                .sum();

        System.out.println(sum + 1);
    }

    private static boolean checkPrimeGenerator(int n, TIntList primes) {
        return IntStream.range(2, (int) Math.sqrt(n))
                .filter(i -> n % i == 0)
                .map(i -> i + (n / i))
                .allMatch(primeCandidate -> primes.binarySearch(primeCandidate) >= 0);
    }
}
