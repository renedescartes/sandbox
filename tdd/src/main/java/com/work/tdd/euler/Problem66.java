package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.Continuations;
import com.work.tdd.euler.util.fraction.Fraction;
import com.work.tdd.euler.util.fraction.Fractions;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem66 {
    private static final Logger logger = Logger.getLogger(Problem66.class.getName());

    public static Fraction diaphontineSolution(int n) {
        if (Utility.isPerfectSquare(n)) {
            return null;
        }
        int term = 1;
        while (true) {
            Fraction f = Continuations.convergentFractions(n, term);
            logger.fine("Attempting [" + f + "] term [" + term + "] n [" + n + "]");
            long num = f.numerator().longValue();
            long den = f.denominator().longValue();
            if ((num * num) - (n * den * den) == 1) {
                logger.info("Solution for n [" + n + "] is [" + f + "]");
                return f;
            }
            term++;
        }
    }

    public static int maximumValueForDiaphontine(int n) {
        BigInteger maximum = BigInteger.ZERO;
        int answer = -1;
        for (int i = 2; i <= n; i++) {
            Fraction f = diaphontineSolution(i);
            BigInteger num = f == null ? null : (BigInteger) f.numerator();
            if (f != null && num.compareTo(maximum) > 0) {
                maximum = num;
                answer = i;
            }
        }
        return answer;
    }

    @Test
    public void testSimple() {
        assertEquals(maximumValueForDiaphontine(7), 5);
        assertEquals(maximumValueForDiaphontine(1000), 661);
    }

    @Test
    public void testBits() {
        assertEquals(diaphontineSolution(13), Fractions.bigIntegerFraction(649, 180));
    }
}
