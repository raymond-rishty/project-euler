package net.rishty.projecteuler.util;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class PrimeSieveTest {

	@Test
	public void testGetSieve() {
		assertEquals(ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19), PrimeSieve.getPrimes(20));
	}

    @Test
    public void testGetSieveCount() {
        assertThat(PrimeSieve.getPrimes(10_000_000).size()).is(664579);
    }

}
