package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import org.apache.commons.math3.primes.Primes;

import java.util.List;

import static com.google.common.collect.Collections2.orderedPermutations;

/**
 * Pandigital prime
 * Problem 41
 * <p>
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
 * <p>
 * What is the largest n-digit pandigital prime that exists?
 */
public class Problem041 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem041().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int pandigitalPrime = getLargestPandigitalPrime();
        System.out.println(pandigitalPrime);
    }

    private int getLargestPandigitalPrime() {
        int integer = orderedPermutations(ImmutableList.of(7, 6, 5, 4, 3, 2, 1), Ordering.natural().reverse())
                .stream()
                .mapToInt(this::toPandigitalInteger)
                .filter(Primes::isPrime)
                .findFirst()
                .getAsInt();

        return integer;
    }

    private Integer toPandigitalInteger(List<Integer> digits) {
        int number = 0;
        for (int digit : digits) {
            number = number * 10 + digit;
        }
        return number;
    }
}
