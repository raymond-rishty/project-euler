package net.rishty.projecteuler.problems;

import java.util.Collections;

import com.google.common.collect.ImmutableList;

import net.rishty.projecteuler.util.PrimeSieve;

public class Problem027 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImmutableList<Integer> sieve = PrimeSieve.getSieve(100000);
		int max = 0;
		int maxA = 0;
		int maxB = 0;

		for (Integer b : sieve) {
			if (b < 1000) {
				for (int a = -1000; a < 1000; a++) {
					int count = countPrimes(a, b, sieve);
					if (count > max) {
						max = count;
						maxA = a;
						maxB = b;
					}
				}
			}
		}

		System.out.printf("(%s, %s) -> %d\t%d%n", maxA, maxB, max, maxA * maxB);
	}

	private static int countPrimes(int a, int b,
			ImmutableList<Integer> sieve) {
		int count = 1;
		
		for (int n = 1;;n++) {
			int candidate = n * n + a * n + b;
			if (Collections.binarySearch(sieve, candidate) <= 0) {
				return count;
			} else {
				count++;
			}
		}
	}

}
