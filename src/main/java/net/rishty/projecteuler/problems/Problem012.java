package net.rishty.projecteuler.problems;

import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

 Let us list the factors of the first seven triangle numbers:

 1: 1
 3: 1,3
 6: 1,2,3,6
 10: 1,2,5,10
 15: 1,3,5,15
 21: 1,3,7,21
 28: 1,2,4,7,14,28
 We can see that 28 is the first triangle number to have over five divisors.

 What is the value of the first triangle number to have over five hundred divisors?
 */
public class Problem012 {
        public void run() {
            OptionalInt first = getFirstTriangularWithDivisorCount(500);

        System.out.println(first);

    }

    private OptionalInt getFirstTriangularWithDivisorCount(int divisorCount) {
        AtomicInteger n = new AtomicInteger(2);

        return IntStream
                .iterate(3, (int t) -> n.incrementAndGet() + t)
                .filter(triangular -> countDivisors(triangular) >= divisorCount)
                .findFirst();
    }

    private int countDivisors(int triangular) {
        int squareRoot = (int) Math.sqrt(triangular);
        int count = 2; // for 1 and triangular

        // for any divisors other than the square root, we add two to the count, for i and n/i are distinct divisors.
        for (int candidate = 2; candidate < squareRoot; candidate++) {
            if (triangular % candidate == 0) {
                count += 2;
            }
        }
        // if the number is a perfect square, the square root
        if (squareRoot * squareRoot == triangular) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        new Problem012().run();
    }
}
