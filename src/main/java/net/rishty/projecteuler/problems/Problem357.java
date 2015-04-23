package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.*;
import net.rishty.projecteuler.util.PrimeSieve;

import java.lang.ref.Reference;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import static com.google.common.collect.Iterables.concat;

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
        ImmutableList<Integer> primes = PrimeSieve.getPrimes(upperBound);
        ImmutableSortedSet<Integer> primeSet = ImmutableSortedSet.copyOf(primes);

        AtomicReference<BigInteger> sum = new AtomicReference<>(BigInteger.ONE);
        //AtomicInteger count = new AtomicInteger(0);
        primeSet.parallelStream().forEach(prime ->  {
            if (prime < upperBound && prime % 4 == 3 && checkPrimeGenerator(prime - 1, primeSet)) {
                sum.accumulateAndGet(BigInteger.valueOf(prime - 1), (a, b) -> a.add(b));
                //count.incrementAndGet();
            }
        });


        System.out.println(sum.get());
    }

    private static boolean checkPrimeGenerator(int n, SortedSet<Integer> primes) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                int primeCandidate = i + (n / i);
                if (!primes.contains(primeCandidate)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkPrimeGenerator_primeFactorsOnly(int n, SortedSet<Integer> primes) {
        if (!primes.contains(n / 2 + 2)) {
            return false;
        }

        if (!primes.contains(1 + n)) {
            return false;
        }


        int r = n;

        for (int prime : primes) {
            /*if (prime > n) {
                return true;
            }*/

            if (r % prime == 0) {
                r /= prime;
                int primeCandidate = prime + (n / prime);
                if (!primes.contains(primeCandidate)) {
                    return false;
                }
            }

            if (r == 1) {
                return true;
            }
        }

        return true;
        /*
        return ImmutableList.copyOf(concat(divisorSieve.get(n), ImmutableList.of(1, n)))
                .stream()
                .map(d -> d + n / d)
                .allMatch(primeCandidate -> isPrime(primeCandidate, divisorSieve));*/
    }
}
