package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

/**
 * Combinatoric selections
 * Problem 53
 * <p>
 * There are exactly ten ways of selecting three from five, 12345:
 * <p>
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * <p>
 * In combinatorics, we use the notation, 5C3 = 10.
 * <p>
 * In general,
 * <p>
 * nCr = n! / r!(n−r)!
 * <p>
 * where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
 * <p>
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * <p>
 * How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?
 */
public class Problem053 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem053().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int count = 0;

        // We are told that 23 is the first row with a value > 1000000
        for (int row = 23; row <= 23; row++) {
            count += findEntriesInRowOverLimit(row, 1_000_000);
        }

        System.out.println(count);
    }

    private int findEntriesInRowOverLimit(int row, int limit) {
        int ncr = row;
        for (int r = 2; r <= 10; r++) {
            // Calculate the binomial value incrementally
            ncr = (row - r + 1) * ncr / r;
            /*
                My trick is to just find the first large result in the row. From there we can easily figure out
                how many are in the row and quickly exit.
            */
            if (ncr > limit) {
                return row - (2 * r) + 1;
            }
        }

        return 0;
    }
}
