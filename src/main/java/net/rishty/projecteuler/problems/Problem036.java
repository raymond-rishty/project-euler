package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.util.stream.IntStream;

/**
 * Double-base palindromes
 * Problem 36
 * <p>
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 * <p>
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 * <p>
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Problem036 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem036().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int sum = findSumOfMultiPalindromes(1_000_000);
        System.out.println(sum);
    }

    private int findSumOfMultiPalindromes(int upperBound) {
        return IntStream.iterate(1, operand -> operand += 2)
                .limit(500)
                .flatMap(i -> IntStream.of(
                        toPalindrome(i, false),
                        toPalindrome(i, true)))
                .filter(this::isBinaryPalindrome)
                .sum();
    }

    boolean isBinaryPalindrome(int i) {
        String binaryString = Integer.toBinaryString(i);
        StringBuilder stringBuilder = new StringBuilder(binaryString);

        return stringBuilder.reverse().toString().equals(binaryString);
    }

    private int toPalindrome(int i, boolean even) {
        String str = Integer.toString(i);
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!even) {
            stringBuilder = stringBuilder.deleteCharAt(str.length() - 1);
        }

        return Integer.parseInt(stringBuilder.reverse().append(str).toString());
    }
}
