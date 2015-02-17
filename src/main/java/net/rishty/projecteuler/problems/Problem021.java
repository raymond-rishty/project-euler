package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.util.Arrays;

/**
 Amicable numbers
 Problem 21

 Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

 For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

 Evaluate the sum of all the amicable numbers under 10000.
 */
public class Problem021 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem021().run();
        System.out.println(stopwatch.stop());
    }

    public int findSumOfAmicablePairs(int upperBound) {
        int[] sieve = new int[upperBound];
        Arrays.fill(sieve, 1);
        int sum = 0;

        for (int i = 2; i < upperBound; i++) {
            for (int j = i + i; j < upperBound; j += i) {
                sieve[j] += i;
            }

            if (sieve[i] < i && sieve[sieve[i]] == i) {
                sum += sieve[i] + i;
            }
        }

        return sum;
    }

    private void run() {
        System.out.println(findSumOfAmicablePairs(10000));
    }
}
