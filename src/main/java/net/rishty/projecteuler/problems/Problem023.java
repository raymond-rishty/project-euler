package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Non-abundant sums
 * Problem 23
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * <p>
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * <p>
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * <p>
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class Problem023 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem023().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        int sum = findSumOfNonAbundantNumbers(28123);
        System.out.println(sum);

    }

    private int findSumOfNonAbundantNumbers(int upperLimit) {
        List<Integer> abundantNumbers = getAbundantNumbers(upperLimit);
        BitSet sieve = new BitSet(upperLimit);
        for (int i = 0; i < abundantNumbers.size(); i++) {
            for (int j = i; j < abundantNumbers.size(); j++) {
                sieve.set(abundantNumbers.get(i) + abundantNumbers.get(j));
            }
        }

        int sum = 0;

        for (int i = 0; i < upperLimit; i++) {
            if (!sieve.get(i)) {
                sum += i;
            }
        }

        return sum;
    }

    private List<Integer> getAbundantNumbers(int upperLimit) {
        List<Integer> abundantNumbers = new ArrayList<>();
        int[] sieve = new int[upperLimit];
        Arrays.fill(sieve, 1);
        for (int i = 2; i < upperLimit; i++) {
            for (int j = i + i; j < upperLimit; j += i) {
                sieve[j] += i;
            }

            if (sieve[i] > i) {
                abundantNumbers.add(i);
            }
        }

        return abundantNumbers;
    }
}
