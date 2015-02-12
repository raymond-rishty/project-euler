package net.rishty.projecteuler.problems;

import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Multisets;

public class Problem005 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Problem005().findPrimeFactors(20));
		System.out.println(new Problem005().findSmallestMultipleOfAllUpTo(20));
	}
	
	public long findSmallestMultipleOfAllUpTo(long n) {
		long product = 1;
		Multiset<Integer> primeFactors = findPrimeFactorsForAllUpTo(n);
		for (Entry<Integer> primeFactor : primeFactors.entrySet()) {
			product *= Math.pow(primeFactor.getElement(), primeFactor.getCount());
		}
		
		for (int i = 1; i < n; i++) {
			System.out.printf("%d %% %d = %d%n", product, i, product % i);
		}
		
		return product;
	}
	
	public Multiset<Integer> findPrimeFactorsForAllUpTo(long n) {
		Multiset<Integer> primeFactors = HashMultiset.create();;
		for (int i = 2; i <= n; i++) {
			Multiset<Integer> primeFactorsForI = findPrimeFactors(i);
			primeFactors = Multisets.union(primeFactors, primeFactorsForI);
			System.out.printf("%d = ", i);
			for (Entry<Integer> primeFactor : primeFactorsForI.entrySet()) {
				System.out.printf("%d^%d ", primeFactor.getElement(), primeFactor.getCount());
			}
			System.out.println();
		}
		
		return primeFactors;
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
