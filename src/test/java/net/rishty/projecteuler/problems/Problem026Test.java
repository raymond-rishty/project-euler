package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class Problem026Test {
    @Test
    public void testGetMaxReciprocalCycleLength() {
        assertThat(new Problem026().getMaxReciprocalCycleLength(10)).is(7);
        assertThat(new Problem026().getMaxReciprocalCycleLength(18)).is(17);
    }

    @Test
    public void testIsPrimitiveRoot() {
        assertThat(new Problem026().isPrimitiveRoot(5)).isFalse();
        assertThat(new Problem026().isPrimitiveRoot(7)).isTrue();
    }
}
