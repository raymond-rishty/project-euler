package net.rishty.projecteuler.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by raymondrishty on 2/23/15.
 */
public class BigDecimalMath {
    /**
     * Square root. R is the square root of A.
     * @param A big decimal.
     * @return the square root of A.
     */
    public static BigDecimal sqrt(BigDecimal A, MathContext mc) {
        if (A == null || A.equals(BigDecimal.ZERO) || A.equals(BigDecimal.ONE)) {
            return A;
        }
        if ( A.signum() < 0 ) {
            throw new ArithmeticException("root of negative not defined");
        }
        // for small A use root of inverse
        if (A.abs().compareTo(BigDecimal.ONE) < 0) {
            BigDecimal Ap = inverse(A);
            Ap = sqrt(Ap, mc);
            Ap = inverse(Ap);
            //System.out.println("sqrt(A).inverse() = " + Ap);
            return Ap;
        }
        // ensure enough precision
        BigDecimal eps = new BigDecimal("0.1"); //e-13"); // TODO
        int p = Math.max(mc.getPrecision(),java.math.MathContext.DECIMAL64.getPrecision());
        //java.math.MathContext.UNLIMITED.getPrecision() == 0
        eps = eps.pow(p / 2);
        // newton iteration
        BigDecimal Ap = new BigDecimal(A.unscaledValue(), mc);
        BigDecimal ninv = new BigDecimal(0.5, mc);
        BigDecimal R1, R = Ap.multiply(ninv); // initial guess
        BigDecimal d;
        int i = 0;
        while (true) {
            R1 = R.add(Ap.divide(R, mc));
            R1 = R1.multiply(ninv); // div n
            d = R.subtract(R1).abs();
            R = R1;
            if (d.compareTo(eps) <= 0) {
                //System.out.println("d  = " + d + ", R = " + R);
                break;
            }
            if (i++ % 11 == 0) {
                eps = eps.add(eps);
            }
            //System.out.println("eps  = " + eps + ", d = " + d);
        }
        return R;
    }

    private static BigDecimal inverse(BigDecimal ap) {
        return BigDecimal.ONE.divide(ap);
    }
}
