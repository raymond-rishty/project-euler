package net.rishty.projecteuler.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import java.util.BitSet;

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

    public static TIntList getPrimesTInt(int upperBound) {
        TIntList primeList = new TIntArrayList();
        BitSet sieve = new BitSet(upperBound);
        sieve.clear();
        sieve.set(0);
        sieve.set(1);

        for (int i = 2; i < upperBound; i++) {
            if (!sieve.get(i)) {
                primeList.add(i);
                for (int j = i + i; j < upperBound && j > 0; j += i) {
                    sieve.set(j);
                }
            }
        }


        return primeList;
    }
}
