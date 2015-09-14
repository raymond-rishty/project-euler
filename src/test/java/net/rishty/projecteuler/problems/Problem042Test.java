package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 9/12/15.
 */
public class Problem042Test {
    @Test
    public void testWordToValue() {
        assertThat(new Problem042().wordToNumberValue("SKY")).is(55);
    }
}
