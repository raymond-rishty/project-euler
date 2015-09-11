package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Pandigital products
 * Problem 32
 * <p>
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
 * <p>
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
 * <p>
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
 * <p>
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class Problem032 {
    public static void main(String[] args) {
        new Problem032().run(9);
    }

    private void run(int digitCount) {
        Stopwatch started = Stopwatch.createStarted();
        List<Integer> digits = IntStream.rangeClosed(1, digitCount).boxed()
                .collect(Collectors.toList());

        TIntSet products = new TIntHashSet();

        Collection<List<Integer>> permutations = Collections2.permutations(digits);
        for (List<Integer> permuation : permutations) {
            int product = toInteger(permuation.subList(0, 4));
            int first = toInteger(permuation.subList(4, 6));
            int second = toInteger(permuation.subList(6, 9));
            if (first * second == product) {
                products.add(product);
            }
        }

        long sum = 0;
        for (TIntIterator iter = products.iterator(); iter.hasNext(); ) {
            int product = iter.next();
            sum += product;
        }

        System.out.printf("generated %d permutations in %d ms%n", permutations.size(), started.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(sum);

    }

    private int toInteger(List<Integer> integers) {
        int n = 0;
        for (int digit : integers) {
            n = n * 10 + digit;
        }

        return n;
    }
}
