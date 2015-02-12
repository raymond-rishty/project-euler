package net.rishty.projecteuler.problems;

import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class Problem002 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long sum = new Problem002().sumEvenTermsToN(4000000);
		System.out.println(sum);
	}
	
	public long sumEvenTermsToN(int upperLimit) {
		return sumIterable(evenTermsToN(upperLimit));
	}
	
	public long sumIterable(Iterable<Integer> iterable) {
		long sum = 0;
		for (Integer integer : iterable) {
			sum += integer;
		}
		
		return sum;
	}
	
	public Iterable<Integer> evenTermsToN(final int upperLimit) {
		return Iterables.filter(termsToN(upperLimit), new Predicate<Integer>() {
			@Override
			public boolean apply(Integer arg0) {
				return ((int) arg0) % 2 == 0;
			}
			
		});
	}
	
	public Iterable<Integer> termsToN(final int upperLimit) {
		final Iterator<Integer> fibonacciIterator = new Iterator<Integer>() {
			int previous = 0;
			int current = 1;
			
			@Override
			public boolean hasNext() {
				return current < upperLimit*2/3;
			}

			@Override
			public Integer next() {
				int next = previous + current;
				previous = current;
				current = next;
				
				return next;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}};
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return fibonacciIterator;
			}
		};
	}
	
	public long findNextTerm(int n1, int n2) {
		return n1 + n2;
	}
}
