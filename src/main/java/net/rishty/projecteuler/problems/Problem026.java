package net.rishty.projecteuler.problems;

import com.google.common.collect.ImmutableList;
import com.google.common.math.BigIntegerMath;
import com.google.common.math.IntMath;
import net.rishty.projecteuler.util.BigDecimalMath;
import net.rishty.projecteuler.util.PrimeSieve;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 Reciprocal cycles
 Problem 26

 A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

 1/2	= 	0.5
 1/3	= 	0.(3)
 1/4	= 	0.25
 1/5	= 	0.2
 1/6	= 	0.1(6)
 1/7	= 	0.(142857)
 1/8	= 	0.125
 1/9	= 	0.(1)
 1/10	= 	0.1
 Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

 Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Problem026 {
    public static void main(String[] args) {
        System.out.println(new Problem026().getMaxReciprocalCycleLength(1000));
    }

    public int getMaxReciprocalCycleLength(int upperBound) {
        ImmutableList<Integer> primes = PrimeSieve.getPrimes(upperBound);
        return primes.stream()
                .mapToInt(Integer::intValue)
                .filter(this::isPrimitiveRoot)
                .max()
                .getAsInt();
    }

    public boolean isPrimitiveRoot(int p) {
        int m = 1;
        int k = 1;
        while ((m = (m * 10) % p) > 1) {
            k++;
        }

        return m == 1 && k == p - 1;
    }
}
