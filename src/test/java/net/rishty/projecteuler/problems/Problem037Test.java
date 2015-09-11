package net.rishty.projecteuler.problems;

import gnu.trove.list.TIntList;
import net.rishty.projecteuler.util.PrimeSieve;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class Problem037Test {
    @Test
    public void testIsTruncatablePrime() {
        TIntList primes = PrimeSieve.getPrimesTInt(10000);
        assertThat(new Problem037().isTruncatablePrime(3797, primes)).isTrue();
        assertThat(new Problem037().isTruncatablePrime(79, primes)).isFalse();
        assertThat(new Problem037().isTruncatablePrime(3113, primes)).isFalse();
    }

    @Test
    public void testIsRightTruncatablePrime() {
        TIntList primes = PrimeSieve.getPrimesTInt(10000);
        assertThat(new Problem037().isRightTruncatable(3797, primes)).isTrue();
        assertThat(new Problem037().isRightTruncatable(79, primes)).isTrue();
        assertThat(new Problem037().isRightTruncatable(97, primes)).isFalse();
        assertThat(new Problem037().isRightTruncatable(131, primes)).isFalse();
        assertThat(new Problem037().isRightTruncatable(3113, primes)).isFalse();
    }

    @Test
    public void testIsLeftTruncatablePrime() {
        TIntList primes = PrimeSieve.getPrimesTInt(10000);
        assertThat(new Problem037().isLeftTruncatable(3797, primes)).isTrue();
        assertThat(new Problem037().isLeftTruncatable(79, primes)).isFalse();
        assertThat(new Problem037().isLeftTruncatable(97, primes)).isTrue();
        assertThat(new Problem037().isLeftTruncatable(131, primes)).isFalse();
        assertThat(new Problem037().isLeftTruncatable(3113, primes)).isFalse();
    }
}
