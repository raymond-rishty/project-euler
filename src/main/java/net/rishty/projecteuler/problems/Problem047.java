package net.rishty.projecteuler.problems;

import com.google.common.collect.ImmutableList;
import net.rishty.projecteuler.util.PrimeSieve;

import java.util.List;

/**
 The first two consecutive numbers to have two distinct prime factors are:

 14 = 2 × 7
 15 = 3 × 5

 The first three consecutive numbers to have three distinct prime factors are:

 644 = 2² × 7 × 23
 645 = 3 × 5 × 43
 646 = 2 × 17 × 19.

 Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
 */
public class Problem047 {
    public static void main(String[] args) {
        ImmutableList<Integer> sieve = PrimeSieve.getPrimes(1000);
        int consecutivesWithPrimeFactors = findConsecutivesWithPrimeFactors(4, 4, sieve);
        System.out.println(consecutivesWithPrimeFactors);
    }

    private static int findConsecutivesWithPrimeFactors(int countToFind, int primeFactorCount, List<Integer> primes) {
        int count = 0;
        for (int i = 2; i < 1000000; i++) {
            if (getDistinctPrimeFactorCount(i, primes) == primeFactorCount) {
                count++;
            } else {
                count = 0;
            }

            if (count == countToFind) {
                return i - count + 1;
            }
        }

        return 0;
    }

    private static int getDistinctPrimeFactorCount(int i, List<Integer> primes) {
        int count = 0;
        for (int prime : primes) {
            if (i % prime == 0) {
                count++;
            }
            if (prime > i) {
                break;
            }
        }

        return count;
    }
}
