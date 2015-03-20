package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.FluentIterable;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import net.rishty.projecteuler.util.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 Square root digital expansion
 Problem 80
 It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.

 The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.

 For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.
 */
public class Problem080 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem080().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(getSumOfDigitsOfIrrationalRoots(100));
    }

    private long getSumOfDigitsOfIrrationalRoots(int max) {
        TIntSet squarefree = getSquareFree(max);
        MathContext mc = new MathContext(105, RoundingMode.HALF_DOWN);
        return IntStream.of(squarefree.toArray())
                .mapToObj(sf -> BigDecimalMath.sqrt(BigDecimal.valueOf(sf), mc))
                .map(BigDecimal::toString)
                .map(sqrtStr -> sqrtStr.replaceFirst("\\.",""))
                .flatMapToInt(sqrtStr -> sqrtStr.chars().limit(100).map(Character::getNumericValue))
                .sum();
    }

    private TIntSet getSquareFree(int max) {
        TIntSet squarefrees = new TIntHashSet(IntStream
                .rangeClosed(1, max)
                .toArray());

        IntStream
                .rangeClosed(1, max)
                .forEach(root -> squarefrees.remove(root * root));

        return squarefrees;
    }
}
