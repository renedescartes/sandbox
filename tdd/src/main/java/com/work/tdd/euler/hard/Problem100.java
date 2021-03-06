package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polygonal.isTriangular;
import static com.work.tdd.euler.util.Polygonal.triangleRoot;

public class Problem100 {

    private static final Logger logger = Logger.getLogger(Problem99.class.getName());

    /**
     * a = 2, b = -2
     *
     * @param MAX
     * @return
     */
    public static void answer(long MAX) {
        BigInteger two = BigInteger.valueOf(2);
        BigInteger factor = BigInteger.valueOf(MAX / 2);
        BigInteger start = factor.multiply(factor.subtract(BigInteger.ONE)).divide(two);
        while (factor.longValue() < MAX) {
            if (factor.mod(BigInteger.valueOf(100000)).intValue() == 0) {
                logger.info("Inspecting " + start + " and factor " + factor);
            }
            BigInteger multiply = start.multiply(two);
            if (isTriangular(multiply)) {
                logger.info("Sets are [" + triangleRoot(start).add(BigInteger.ONE) + "] and [" + triangleRoot(multiply).add(BigInteger.ONE) + "]");
            }
            start = start.add(factor);
            factor = factor.add(BigInteger.ONE);
        }
    }

    @Test
    public void testSimple() {
        answer(1_000_000_000_000L);
    }

}
