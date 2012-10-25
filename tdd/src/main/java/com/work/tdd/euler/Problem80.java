package com.work.tdd.euler;

import com.work.tdd.euler.util.NumberUtil;
import com.work.tdd.euler.util.fraction.Continuations;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.NumberUtil.digits;
import static com.work.tdd.euler.util.NumberUtil.summation;
import static org.testng.Assert.assertEquals;

public class Problem80 {
    private static final Logger logger = Logger.getLogger(Problem79.class.getName());

    public long summationOfDigits(int n, int digits) {
        String str = Continuations.squareRoot(n, digits).toString().replaceAll("\\.", "");
        String fractionalString = str.substring(0, Math.min(str.length(), 100));
        return summation(digits(new BigInteger(fractionalString)));
    }

    public long answer(int MAX, int digits) {
        long sum = 0;
        for (int i = 1; i <= MAX; i++) {
            sum += summationOfDigits(i, digits);
        }
        return sum;
    }

    @Test
    public void testBits() {
        assertEquals(NumberUtil.summation(digits(new BigInteger("4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727"))), 481);
        assertEquals(summationOfDigits(2, 100), 475);
        assertEquals(answer(100, 100), 40932);
    }


}
