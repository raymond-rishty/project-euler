package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import net.rishty.projecteuler.util.PrimeSieve;

import java.math.RoundingMode;
import java.util.BitSet;

/**
 * Goldbach's other conjecture
 * Problem 46
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
 * <p>
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 * <p>
 * It turns out that the conjecture was false.
 * <p>
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
public class Problem046 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem046().run();
        System.out.println(stopwatch);
    }

    public void run() {
        System.out.println(getFirstNonConformingOddComposite());
    }

    private int getFirstNonConformingOddComposite() {
        int upperLimit = 10000;

        BitSet bitSet = findConformingNumbers(upperLimit);

        // Loop through odd numbers in the sieve until we find a unmarked number
        // We can start at 9 because it is the first odd composite
        for (int i = 9; i < upperLimit; i += 2) {
            if (!bitSet.get(i)) {
                return i;
            }
        }

        return 0;
    }

    /* Calculate each number that conforms to the pattern of the conjecture, and mark it as found in the bit set
     */
    private BitSet findConformingNumbers(int upperLimit) {
        BitSet bitSet = new BitSet(upperLimit);
        ImmutableList<Integer> primes = PrimeSieve.getPrimes(upperLimit);
        for (int prime : primes) {
            int i2 = 0;
            int sbl = IntMath.sqrt((upperLimit - prime) / 2, RoundingMode.CEILING);
            for (int i = 1; i < sbl; i++) {
                i2 = i2 + 2 * i - 1;
                bitSet.set(prime + 2 * i2);
            }
            bitSet.set(prime);
        }
        return bitSet;
    }
}
