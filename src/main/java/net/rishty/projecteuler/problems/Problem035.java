package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import net.rishty.projecteuler.util.PrimeSieve;

/**
 * Circular primes
 * Problem 35
 * <p>
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * <p>
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * <p>
 * How many circular primes are there below one million?
 */
public class Problem035 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem035().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int count = findCircularPrimesBelow(1_000_000);
        System.out.println(count);
    }

    int findCircularPrimesBelow(int upperLimit) {
        TIntList primesTInt = PrimeSieve.getPrimesTInt(upperLimit);
        int count = 0;
        for (TIntIterator iter = primesTInt.iterator(); iter.hasNext(); ) {
            int prime = iter.next();
            if (isCircular(prime, primesTInt)) {
                count++;
            }
        }

        return count;
    }

    boolean isCircular(int prime, TIntList primesTInt) {
        TIntList digits = getDigits(prime);

        int digitCount = digits.size();
        for (int rotation = 0; rotation < digitCount - 1; rotation++) {
            digits.add(digits.removeAt(0)); // rotate
            if (primesTInt.binarySearch(toInt(digits)) < 0) {
                return false;
            }
        }

        return true;
    }

    private TIntList getDigits(int prime) {
        TIntList digits = new TIntArrayList();
        int number = prime;
        while (number >= 1) {
            digits.add(number % 10);
            number /= 10;
        }

        digits.reverse();
        return digits;
    }

    private int toInt(TIntList digits) {
        int value = 0;
        for (TIntIterator iter = digits.iterator(); iter.hasNext(); ) {
            int digit = iter.next();
            value *= 10;
            value += digit;
        }

        return value;
    }

}
