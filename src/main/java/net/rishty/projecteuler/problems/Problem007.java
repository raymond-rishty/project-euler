package net.rishty.projecteuler.problems;

import com.google.common.collect.ImmutableList;
import net.rishty.projecteuler.util.PrimeSieve;

/**
 * Created by raymondrishty on 2/13/15.
 */
public class Problem007 {
    public static void main(String[] args) {
        new Problem007().run();
    }

    private void run() {
        ImmutableList<Integer> primes = PrimeSieve.getSieve(1000000);
        Integer prime = primes.get(10000);
        System.out.println(prime);
    }
}
