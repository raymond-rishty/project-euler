package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import net.rishty.projecteuler.util.PrimeSieve;

public class Problem010 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ImmutableList<Integer> primes = PrimeSieve.getSieve(2_000_000);
        long sum = primes.stream().mapToLong(Integer::longValue).sum();
        System.out.println(sum);
        System.out.println(stopwatch.stop());
    }
}
