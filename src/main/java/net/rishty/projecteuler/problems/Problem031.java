package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;

import java.util.Arrays;

/**
 * Coin sums
 * Problem 31
 * <p>
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
 * <p>
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * It is possible to make £2 in the following way:
 * <p>
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * How many different ways can £2 be made using any number of coins?
 */
public class Problem031 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem031().run();
        System.out.println(stopwatch);
    }

    private void run() {
        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
        System.out.println(combinations(coins, 200));
    }

    private static long combinations(int[] coins, int targetValue) {

        int[] ways = new int[targetValue + 1];
        Arrays.fill(ways, 0);
        ways[0] = 1;

        /* Try to figure out how many ways to make the target with 1p coins, then 1p and 2p, then 1p, 2p, and 5p, etc.,
        building up the solution
         */
        for (int coinValue : coins) {
            for (int amount = coinValue; amount <= targetValue; amount++) {
                ways[amount] = ways[amount] + ways[amount - coinValue];
            }
        }

        return ways[targetValue];
    }
}
