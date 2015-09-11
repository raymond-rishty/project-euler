package net.rishty.projecteuler.problems;

import com.google.common.truth.Truth;
import net.rishty.projecteuler.util.PrimeSieve;
import org.junit.Test;

public class Problem035Test {
    @Test
    public void testPrimeExample() {
        boolean circular = new Problem035().isCircular(197, PrimeSieve.getPrimesTInt(1000));
        Truth.assertThat(circular).isTrue();
    }

    @Test
    public void testPrimeCount() {
        int count = new Problem035().findCircularPrimesBelow(100);
        Truth.assertThat(count).is(13);
    }

}
