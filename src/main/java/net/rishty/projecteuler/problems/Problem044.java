package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import java.util.BitSet;

/**
 * Pentagon numbers
 * Problem 44
 * <p>
 * Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten pentagonal numbers are:
 * <p>
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 * <p>
 * It can be seen that P_4 + P_7 = 22 + 70 = 92 = P_8. However, their difference, 70 − 22 = 48, is not pentagonal.
 * <p>
 * Find the pair of pentagonal numbers, P_j and P_k, for which their sum and difference are pentagonal and D = |P_k − P_j|
 * is minimised; what is the value of D?
 */
public class Problem044 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem044().run();
        System.out.println(stopwatch);
    }

    private void run() {
        long pair = findClosestPentagonalPair();
        System.out.println(pair);
    }

    private long findClosestPentagonalPair() {
        int minDiff = Integer.MAX_VALUE;
        int[] pentagonals = generatePentagonals();
        BitSet pentagonalLookup = toBitset(pentagonals);
        for (int kd = 1; kd < pentagonals.length; kd++) {
            for (int j = 0; j < pentagonals.length - 1 - kd; j++) {
                int k = j + kd;
                int pj = pentagonals[j];
                int pk = pentagonals[k];

                int sum = pj + pk;
                if (sum < 0) {
                    break;
                }

                if (pentagonalLookup.get(sum)) {
                    int diff = pk - pj;
                    if (pentagonalLookup.get(diff)) {
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }
            }
        }

        return minDiff;
    }

    private BitSet toBitset(int[] pentagonals) {
        BitSet bitSet = new BitSet(pentagonals[pentagonals.length - 1]);
        for (int pentagonal : pentagonals) {
            bitSet.set(pentagonal);
        }

        return bitSet;
    }

    private int[] generatePentagonals() {
        TIntList list = new TIntArrayList(3000);
        int n = 1;
        int p = 1;
        list.add(1);

        while (n < 3000) {
            p = p + 3 * (++n) - 2;
            list.add(p);
        }

        return list.toArray();
    }
}