package net.rishty.projecteuler.problems;

import com.google.common.math.IntMath;

import java.util.stream.IntStream;

import static java.math.RoundingMode.FLOOR;

/**
 * Digit fifth powers
 * Problem 30
 * <p>
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 * <p>
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * As 1 = 1^4 is not a sum it is not included.
 * <p>
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * <p>
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class Problem030 {
    public int[] powers(int exponent) {
        return IntStream.rangeClosed(0, 9)
                .map(base -> IntMath.pow(base, exponent))
                .toArray();
    }

    public int[] digits(int number) {
        int r = number;
        int[] digits = new int[1 + IntMath.log10(number, FLOOR)];
        int i = 0;
        while (r > 0) {
            digits[i++] = r % 10;
            r /= 10;
        }

        return digits;
    }

    public int sumOfDigitPowers(int[] digits, int[] powers) {
        return IntStream.of(digits)
                .map(digit -> powers[digit])
                .sum();
    }

    public static void main(String[] args) {
        new Problem030().run(5);

    }

    private void run(int exponent) {
        int[] powers = powers(exponent);
        int sum = IntStream.rangeClosed(2, 999999)
                .filter(num -> sumOfDigitPowers(digits(num), powers) == num)
                .sum();
        System.out.println(sum);
    }

}
