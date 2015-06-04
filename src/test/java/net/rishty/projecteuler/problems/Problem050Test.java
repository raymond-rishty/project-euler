package net.rishty.projecteuler.problems;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class Problem050Test {
  @Test
  public void testRun() {
    assertThat(new Problem050().getSumOfConsecutivePrimes(100)).is(41);
    assertThat(new Problem050().getSumOfConsecutivePrimes(1000)).is(953);
  }

}
