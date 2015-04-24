package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 4/24/15.
 */
public class Problem028Test {
    @Test
    public void testGetSumToSquareSize() {
        assertThat(new Problem028().getSumToSquareSize(5)).is(101);
    }
}
