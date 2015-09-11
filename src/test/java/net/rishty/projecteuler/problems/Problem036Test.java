package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 9/10/15.
 */
public class Problem036Test {
    @Test
    public void testIsBinaryPalindrome() {
        assertThat(new Problem036().isBinaryPalindrome(2)).isFalse();
        assertThat(new Problem036().isBinaryPalindrome(3)).isTrue();
        assertThat(new Problem036().isBinaryPalindrome(585)).isTrue();
    }
}
