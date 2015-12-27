package net.rishty.projecteuler.problems;

import org.junit.Test;

import java.math.BigInteger;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 12/27/15.
 */
public class Problem057Test {
    @Test
    public void testNext() {
        Problem057 problem058 = new Problem057();
        Problem057.Fraction a1 = getFraction(3, 2);
        Problem057.Fraction a2 = a1.next();
        assertThat(a2).isEqualTo(getFraction(7, 5));
        Problem057.Fraction a3 = a2.next();
        assertThat(a3).isEqualTo(getFraction(17, 12));
        Problem057.Fraction a4 = a3.next();
        assertThat(a4).isEqualTo(getFraction(41, 29));
        Problem057.Fraction a5 = a4.next();
        assertThat(a5).isEqualTo(getFraction(99, 70));
        Problem057.Fraction a6 = a5.next();
        assertThat(a6).isEqualTo(getFraction(239, 169));
        Problem057.Fraction a7 = a6.next();
        assertThat(a7).isEqualTo(getFraction(577, 408));
        Problem057.Fraction a8 = a7.next();
        assertThat(a8).isEqualTo(getFraction(1393, 985));
    }

    @Test
    public void testDifferentDigitLength() {
        Problem057 problem058 = new Problem057();
        assertThat(problem058.isDigitLengthDifferent(getFraction(3, 2))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(7, 5))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(17, 12))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(41, 29))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(99, 70))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(239, 169))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(577, 408))).isFalse();
        assertThat(problem058.isDigitLengthDifferent(getFraction(1393, 985))).isTrue();
    }

    private Problem057.Fraction getFraction(int numerator, int denominator) {
        return new Problem057.Fraction(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }
}
