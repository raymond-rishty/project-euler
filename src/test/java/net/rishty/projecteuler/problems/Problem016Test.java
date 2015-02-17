package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 2/15/15.
 */
public class Problem016Test {
    @Test
    public void test() {
        assertThat(new Problem016().sumOfDigitsOfPowerOf2(15)).is(26);
    }
}
