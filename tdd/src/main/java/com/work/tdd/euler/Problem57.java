package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.addFractions;
import static com.work.tdd.euler.Utility.reciprocal;
import static org.testng.Assert.assertEquals;

public class Problem57 {
    private static final Logger logger = Logger.getLogger(Problem57.class.getName());
    private static BigIntegerFraction TWO = new BigIntegerFraction(2, 1);
    private static BigIntegerFraction ONE = new BigIntegerFraction(1, 1);

    public static BigIntegerFraction findFraction(int term, BigIntegerFraction rightFraction) {
        return term == 1 ? addFractions(ONE, reciprocal(rightFraction)) : findFraction(term - 1, addFractions(TWO, reciprocal(rightFraction)));
    }

    public static BigIntegerFraction findFraction(int term) {
        return findFraction(term, TWO);
    }

    public static int answer(int MAX) {
        int answer = 0;
        for (int i = 1; i <= MAX; i++) {
            BigIntegerFraction f = findFraction(i);
            logger.info("i = " + i + " f = " + f);
            if (f.numerator.toString().length() > f.denominator.toString().length()) {
                answer++;
            }
        }
        return answer;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(1000), 153);
        assertEquals(findFraction(1), new BigIntegerFraction(3, 2));
        assertEquals(findFraction(2), new BigIntegerFraction(7, 5));
        assertEquals(findFraction(3), new BigIntegerFraction(17, 12));
        assertEquals(findFraction(4), new BigIntegerFraction(41, 29));
    }
}
