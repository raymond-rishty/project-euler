package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 10/2/15.
 */
public class Problem054Test {

    @Test
    public void test5H() throws Exception {
        assertThat(new Problem054().compare("5H 5C 6S 7S KD", "2C 3S 8S 8D TD")).isEqualTo(2);
    }

    @Test
    public void test5D() throws Exception {
        assertThat(new Problem054().compare("5D 8C 9S JS AC", "2C 5C 7D 8S QH")).isEqualTo(1);
    }

    @Test
    public void test2D() throws Exception {
        assertThat(new Problem054().compare("2D 9C AS AH AC", "3D 6D 7D TD QD")).isEqualTo(2);
    }

    @Test
    public void test4D() throws Exception {
        assertThat(new Problem054().compare("4D 6S 9H QH QC", "3D 6D 7H QD QS")).isEqualTo(1);
    }

    @Test
    public void test2H() throws Exception {
        assertThat(new Problem054().compare("2H 2D 4C 4D 4S", "3C 3D 3S 9S 9D")).isEqualTo(1);
    }

}
