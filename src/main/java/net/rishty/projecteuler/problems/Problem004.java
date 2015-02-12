package net.rishty.projecteuler.problems;

import java.util.List;

import com.google.common.collect.Lists;

public class Problem004 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(new Problem004().largestPalindromeProduct(3));
		long end = System.nanoTime();
		System.out.printf("%dms%n",(end-start) / 1000000);
	}
	
	public List<Integer> getFields(int digits) {
		List<Integer> fields = Lists.newArrayList();
		for (int i = 0; i < digits * 2; i++) {
			fields.add((int)Math.pow(10, i));
		}
		
		return fields;
	}

	public int largestPalindromeProduct(int digits) {
		int largePalindrome = 0;
		
		int upper = (int) Math.pow(10, digits) - 1;
		int lower = (int) Math.pow(10, digits - 1);
		List<Integer> fields = getFields(digits);
		
		for (int i = upper; i > lower; i--) {
			int mod = i % 11;
			int step = mod > 0 ? 11 : 1;
			
			for (int j = i - mod; j > lower; j -= step) {
				int candidate = i * j;
				if (candidate < largePalindrome) {
					break;
				} else if (isPalindrome(candidate, digits, fields)) {
					largePalindrome = Math.max(largePalindrome, candidate);
				}
			}
		}
		
		return largePalindrome;
	}

	private boolean isPalindrome(int i, int digits, List<Integer> fields) {
		boolean isPalindrome = true;
		int last = 2 * digits;
		
		for (int position = 1; position <= digits; position++) {
			isPalindrome &= (i % fields.get(position)) / fields.get(position - 1) == (i / fields.get(last - position)) % 10;
		}

		return isPalindrome;
	}
}
