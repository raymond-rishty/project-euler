package net.rishty.projecteuler.problems;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class Problem095 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int upperBound = 1000000;
		int max = 0;
		ImmutableMap<Integer, Integer> divisorSums = new Problem095().findSumsOfDivisorsUpTo(upperBound);
		Set<Integer> divergentValues = Sets.newHashSet();
		for (int i = 2; i < upperBound; i++) {
			int chainLengths = new Problem095().getChainLengths(i, upperBound, divisorSums, divergentValues);
			if (chainLengths > max) {
				max = chainLengths;
				System.out.printf("%d: %d%n", i, chainLengths);
			}
		}
		
		System.out.println(divisorSums.get(12496) - 12496);
		System.out.println(new Problem095().getChainLengths(12496, upperBound, divisorSums, divergentValues));
	}
	
	public int getChainLengths(int n, int upperBound, ImmutableMap<Integer, Integer> divisorSums, Set<Integer> divergentValues) {
		int next = n;
		int count = 0;
		Set<Integer> chainValues = Sets.newHashSet();
		do {
			int prev = next;
			next = divisorSums.get(next) - next;
			if (prev == next) {
				break;
			}
			
			if (chainValues.contains(next)) {
				break;
			}

			chainValues.add(next);
			
			count++;
			if (divergentValues.contains(next)) {
				divergentValues.addAll(chainValues);
				return 0;
			}
			
		} while (next > 0 && next != n && next < upperBound);
		
		if (n != next) {
			divergentValues.addAll(chainValues);
		}
		
		return count;
	}
	
	public ImmutableMap<Integer, Integer> findSumsOfDivisorsUpTo(int upperBound) {
		Map<Integer, Integer> knownSums = Maps.newHashMap(ImmutableMap.of(1, 1, 2, 3, 3, 4, 4, 7));
		for (int n = 1 ; n < upperBound; n++) {
			findSumOfDivisors(n, knownSums);
		}
		
		return ImmutableMap.copyOf(knownSums);
	}
	
	public int findSumOfDivisors(int n, Map<Integer, Integer> knownSums) {
		if (knownSums.containsKey(n)) {
			return knownSums.get(n);
		} else if (n < 0) {
			return 0;
		} else {
			int i = 1;
			int a = 1;
			int sum = 0;

			while (a <= n) {
				final int sumOfDivisors;
				
				if (a == n) {
					sumOfDivisors = n;
				} else {
					sumOfDivisors = findSumOfDivisors(n - a, knownSums);
				}
				
				if ((i-1) % 4 < 2) {
					sum += sumOfDivisors;
				} else {
					sum -= sumOfDivisors;
				}
				
				a += (++i % 2 == 0 ? i / 2 : i);
			}
			
			knownSums.put(n, sum);
			return sum;
		}
	}

	
	public Multiset<Integer> findPrimeFactors(long n) {
		List<Integer> factors = findFactors(n);
		if (factors.size() == 0) {
			return ImmutableMultiset.of((int) n);
		} else if (factors.size() == 1) {
			return ImmutableMultiset.copyOf(factors);
		}
		
		Multiset<Integer> primeFactors = HashMultiset.create();
		
		for (int factor : factors) {
			primeFactors.addAll(findPrimeFactors(factor));
		}
		
		return primeFactors;
	}

	
	public List<Integer> findFactors(long n) {
		List<Integer> factors = Lists.newArrayList();
		for (int candidate = (int) n - 1; candidate > 1; candidate--) {
			while (n % candidate == 0) {
				n /= candidate;
				factors.add(candidate);
			}
		}
		
		return factors;
	}

}
