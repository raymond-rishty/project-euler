package net.rishty.projecteuler.problems;

import java.math.BigInteger;

public class Problem048 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Problem048().sumSelfPrimes(1000));
	}

	private long sumSelfPrimes(int n) {
		BigInteger sum = BigInteger.ZERO;
		BigInteger tenDigits = BigInteger.TEN.pow(10);
		for (int i = 1; i <= n; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			sum = sum.add(bi.modPow(bi, tenDigits));
		}
		
		return sum.mod(tenDigits).longValue();
	}

}
