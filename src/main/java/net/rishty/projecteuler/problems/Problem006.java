package net.rishty.projecteuler.problems;

public class Problem006 {
    public static void main(String[] args) {
        System.out.println(getDifference(100L));
    }

    private static long getDifference(long n) {
        long a = n*(n + 1) * (2 * n + 1) / 6;
        long b = n*(n + 1) / 2;
        return b*b-a;
    }
}
