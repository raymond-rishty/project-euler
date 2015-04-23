package net.rishty.projecteuler.util;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import com.google.common.base.Stopwatch;
import com.google.common.collect.*;
import com.google.common.collect.ImmutableList.Builder;

public class PrimeSieve {
    public static ImmutableList<Integer> getPrimes(int upperBound) {
        Builder<Integer> primeListBuilder = ImmutableList.builder();
        BitSet sieve = new BitSet(upperBound);
        sieve.clear();
        sieve.set(0);
        sieve.set(1);

        for (int i = 2; i < upperBound; i++) {
            if (!sieve.get(i)) {
                primeListBuilder.add(i);
                for (int j = i + i; j < upperBound && j > 0; j += i) {
                    sieve.set(j);
                }
            }
        }


        return primeListBuilder.build();
    }
}
