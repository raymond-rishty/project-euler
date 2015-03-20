package net.rishty.projecteuler.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by raymondrishty on 2/23/15.
 */
public class BigDecimalMathTest {
    @Test
    public void sqrtTest() {
        MathContext mc = new MathContext(100, RoundingMode.HALF_EVEN);
        BigDecimal sqrt2 = BigDecimalMath.sqrt(BigDecimal.valueOf(2L), mc);
        assertThat(sqrt2.toString()).startsWith("1.414213562373095048801688724209698078569671875376948073176679737990732478462107038850387534327641572");
        System.out.println(sqrt2);
    }
}
