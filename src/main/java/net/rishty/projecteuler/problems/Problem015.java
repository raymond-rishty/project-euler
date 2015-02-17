package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * <p>
 * RRDD, RDRD, RDDR,
 * DRRD, DRDR, DDRR
 * <p>
 * How many such routes are there through a 20×20 grid?
 */
public class Problem015 {
    public long countRoutes(int length) {
        if (length == 0) {
            return 1;
        }

        long prev = countRoutes(length - 1);

        return 4 * prev - 2 * (prev / length);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem015().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(countRoutes(20));
    }


}
