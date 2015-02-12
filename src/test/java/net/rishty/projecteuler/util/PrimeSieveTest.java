package net.rishty.projecteuler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class PrimeSieveTest {

	@Test
	public void testGetSieve() {
		assertEquals(ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19), PrimeSieve.getSieve(20));
	}

}
