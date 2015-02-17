package net.rishty.projecteuler.problems;

import gnu.trove.map.hash.TLongIntHashMap;

import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * <p>
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * <p>
 * Using the rule above and starting with 13, we generate the following sequence:
 * <p>
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * <p>
 * Which starting number, under one million, produces the longest chain?
 * <p>
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class Problem014 {
    public long nextCollatz(long n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3 * n + 1;
        }
    }

    public int collatzLengthRecursive(long n, TLongIntHashMap lengths) {
        if (!lengths.containsKey(n)) {
            lengths.put(n, 1 + collatzLengthRecursive(nextCollatz(n), lengths));
        }

        return lengths.get(n);
    }

    public int collatzLengthIterative(long n, TLongIntHashMap lengths) {
        int length = 0;
        long i = n;
        do {
            i = nextCollatz(i);
            length++;
        } while (!lengths.containsKey(i));

        length += lengths.get(i);
        lengths.put(n, length);

        return length;
    }

    public void run() {
        TLongIntHashMap lengths = new TLongIntHashMap();
        lengths.put(1, 1);
        int maxStart = IntStream.range(1, 1_000_000)
                .reduce(new IntBinaryOperator() {
                    int maxLength = 0;

                    @Override
                    public int applyAsInt(int left, int right) {
                        int length = collatzLengthRecursive(right, lengths);
                        if (length > maxLength) {
                            maxLength = length;
                            return right;
                        }

                        return left;
                    }
                }).getAsInt();
        System.out.println(maxStart);
    }

    public static void main(String[] args) {
        //TLongIntHashMap lengths = new TLongIntHashMap();
        //lengths.put(1,1);
        //System.out.println(new Problem014().collatzLength(13, lengths));
        new Problem014().run();
    }
}
