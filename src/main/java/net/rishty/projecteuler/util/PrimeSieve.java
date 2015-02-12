package net.rishty.projecteuler.util;

import java.util.Arrays;
import java.util.BitSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class PrimeSieve {
	public static ImmutableList<Integer> getSieve(int upperBound) {
		Builder<Integer> primeListBuilder = ImmutableList.builder();
        BitSet sieve = new BitSet(upperBound);
        sieve.clear();
        sieve.set(0);
        sieve.set(1);

		for (int i = 2; i < upperBound; i++) {
            if (!sieve.get(i)) {
                primeListBuilder.add(i);
                for (int j = i * i; j < upperBound; j += i) {
                    sieve.set(j);
                }
            }
		}
		
		
		
		return primeListBuilder.build();
	}
}
