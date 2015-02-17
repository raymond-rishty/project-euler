package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Chars;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Power Digit Sum
 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

 What is the sum of the digits of the number 2^1000?
 */
public class Problem016 {
    public int sumOfDigitsOfPowerOf2(int exponent) {
        char[] chars = BigInteger.valueOf(2).pow(exponent).toString(10).toCharArray();
        return Chars.asList(chars).stream()
                .mapToInt(Character::getNumericValue)
                .sum();
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem016().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(sumOfDigitsOfPowerOf2(1000));
    }
}
