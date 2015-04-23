package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 4/23/15.
 */
public class Problem017Test {
    @Test
    public void test() {
        assertThat(new Problem017().getLengthForNumber(342)).is(23);
        assertThat(new Problem017().getLengthForNumber(115)).is(20);
    }
}
