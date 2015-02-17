package net.rishty.projecteuler.problems;

import com.google.common.truth.Truth;
import net.rishty.projecteuler.problems.Problem015;
import org.junit.Test;

/**
 * Created by raymondrishty on 2/15/15.
 */
public class Problem015Test {
    @Test
    public void test() {
        Problem015 problem015 = new Problem015();
        Truth.assertThat(problem015.countRoutes(1)).is(2);
        Truth.assertThat(problem015.countRoutes(2)).is(6);
        Truth.assertThat(problem015.countRoutes(3)).is(20);

    }
}
