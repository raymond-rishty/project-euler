package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

import java.util.List;

import static com.google.common.collect.Collections2.orderedPermutations;

/**
 * Pandigital multiples
 * Problem 38
 * <p>
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * <p>
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
 * <p>
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
 * <p>
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
 */
public class Problem038 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem038().run();
        System.out.println(stopwatch);
    }

    public void run() {
        Integer integer = orderedPermutations(ImmutableList.of(9, 8, 7, 6, 5, 4, 3, 2, 1), Ordering.natural().reverse())
                .stream()
                .map(this::toPandigitalInteger)
                .filter(this::isPandigitalMultiple)
                .findFirst()
                .get();
        System.out.println(integer);
    }

    private boolean isPandigitalMultiple(Integer pandigital) {
        int upperEnd = pandigital / 100000;
        int lowerEnd = pandigital % 100000;
        return lowerEnd == 2 * upperEnd;
    }

    private Integer toPandigitalInteger(List<Integer> digits) {
        int number = 0;
        for (int digit : digits) {
            number = number * 10 + digit;
        }
        return number;
    }
}
