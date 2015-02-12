package net.rishty.projecteuler;

import sun.jvm.hotspot.utilities.Assert;

/**
 * Created by raymondrishty on 12/17/14.
 */
public class FermatFactorization {
  public static void main(String[] args) {
      new FermatFactorization().run();
  }

    private void run() {
        factor(76);
    }

    private void factor(int i) {
        int sq = (int) Math.sqrt(i);
        int b2 = findPreviousPerfectSquare(sq);
        while (!isSquare(i - b2)) {
            b2 = (int) Math.pow(Math.sqrt(b2) + 1, 2);
        }
            int a2 = i - b2;
            int a = (int) Math.sqrt(a2);
            int b = (int) Math.sqrt(b2);
            int m = a + b;
            int n = a - b;
            Assert.that(m * n == i, "Invalid solution");
            System.out.printf("%s = %s * %s%n", i, m, n);

    }

    private boolean isSquare(int i) {
        // TODO: this is a naive implementation. Can do better.
        return findPreviousPerfectSquare(i) == i;
    }

    private int findPreviousPerfectSquare(int sq) {
        return (int) Math.pow(Math.floor(Math.sqrt(sq)), 2);
    }
}
