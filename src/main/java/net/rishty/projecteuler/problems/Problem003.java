package net.rishty.projecteuler.problems;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;

public class Problem003 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Problem003().findLargestPrimeFactor(600851475143L));
		System.out.println(new Problem003().findLargestFactor(600851475143L));
	}
	
	public int findLargestPrimeFactor(long n) {
		return Ordering.natural().max(findPrimeFactors(n));
	}
	
	public int findLargestFactor(long n) {
		int largestFactor = 1;
		if (n % 2 == 0) {
			n /= 2;
			largestFactor = 2;
		}

		for (int i = 3; i < n; i+= 2) {
			if (n % i == 0) {
				largestFactor = i;
				n /= i;
			}
		}
		
		
		return Math.max(largestFactor, (int) n);
	}
	
	private static class DividesPredicate implements Predicate<Integer> {
		private final int n;
		
		public DividesPredicate(int n) {
			this.n = n;
		}
		
		public boolean apply(Integer arg0) {
			return n % arg0 == 0;
		}
	}
	
	public boolean isPrime(final int n) {
		return !Iterables.any(ContiguousSet.create(Range.closedOpen(2, (int) Math.sqrt(n)), DiscreteDomain.integers()), new DividesPredicate(n));
	}
	
	public List<Integer> findPrimeFactors(long n) {
		List<Integer> factors = findFactors(n);
		if (factors.size() == 0) {
			return ImmutableList.of((int) n);
		} else if (factors.size() == 1) {
			return factors;
		}
		
		List<Integer> primeFactors = Lists.newArrayList();
		
		for (int factor : factors) {
			primeFactors.addAll(findPrimeFactors(factor));
		}
		
		return primeFactors;
	}
	
	public List<Integer> findFactors(long n) {
		List<Integer> factors = Lists.newArrayList();
		for (int candidate = (int) Math.sqrt(n); candidate > 1; candidate--) {
			if (n % candidate == 0) {
				n /= candidate;
				factors.add(candidate);
			}
		}
		
		return factors;
	}

}
