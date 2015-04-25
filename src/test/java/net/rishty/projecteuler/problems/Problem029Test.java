package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class Problem029Test {
    @Test
    public void testGetDistinctTermCount() {
        assertThat(new Problem029().getDistinctTermCount(5)).is(15);
    }
}
