package net.rishty.projecteuler.problems;

import java.util.Collection;

import org.apache.commons.math3.util.ArithmeticUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Problem033 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<Rational> digitCancellingFractions = new Problem033().getDigitCancellingFractions();
		System.out.println(Joiner.on(", ").join(digitCancellingFractions));
		System.out.println(multiplyRationals(digitCancellingFractions));
	}
	
	public static Rational addRationals(Iterable<Rational> rationals) {
		Rational sum = Rational.of(0,  1);
		for (Rational rational : rationals) {
			sum = addRationals(sum, rational);
		}
		
		return sum;
	}
	
	public static Rational multiplyRationals(Iterable<Rational> rationals) {
		Rational sum = Rational.of(1,  1);
		for (Rational rational : rationals) {
			sum = multiplyRationals(sum, rational);
		}
		
		return sum;
	}
	
	public static Rational addRationals(Rational r1, Rational r2) {
		int lcm = ArithmeticUtils.lcm(r1.getDenominator(), r2.getDenominator());
		return Rational.of(r1.getNumerator() * (lcm / r1.getDenominator()) + r2.getNumerator() * (lcm / r2.getDenominator()), lcm);
	}
	
	public static Rational multiplyRationals(Rational r1, Rational r2) {
		Rational product = Rational.of(r1.getNumerator() * r2.getNumerator(), r1.getDenominator() * r2.getDenominator());
		return product.reduce();
	}
	
	static class Rational {
		int numerator;
		int denominator;
		
		public Rational(int numerator, int denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		public static Rational of(int numerator, int denominator) {
			return new Rational(numerator, denominator);
		}
		
		public int getNumerator() {
			return numerator;
		}
		
		public int getDenominator() {
			return denominator;
		}
		
		public Rational reduce() {
			int gcd = ArithmeticUtils.gcd(getNumerator(),  getDenominator());
			return Rational.of(getNumerator() / gcd, getDenominator() / gcd);
		}
		
		@Override
		public String toString() {
			if (getDenominator() == 1) {
				return Integer.toString(numerator);
			} else {
				return String.format("%d/%d", numerator, denominator);
			}
		}
	}
	
	public Collection<Rational> getDigitCancellingFractions() {
		Collection<Rational> rationals = Lists.newArrayList();
		for (int i = 11; i < 100; i++) {
			if (i % 10 != 0 && i % 10 != i / 10) {
				for (int j = 10 + i / 10; j < 100; j += 10) {
					if (i != j) {
						int gcd = ArithmeticUtils.gcd(i,  j);
						int gcd2 = ArithmeticUtils.gcd(i % 10, j / 10);
						boolean match = i/gcd == (i % 10) / gcd2 && j /gcd == (j / 10) / gcd2;
						System.out.printf("%d / %d = %d / %d %s= %d / %d%n", i, j, i/gcd, j/gcd, match ? "=" : "!", i % 10, j / 10);
						if (match) {
							rationals.add(Rational.of(i,  j));
						}
					}
				}
			}
		}
		
		return rationals;
	}

}
