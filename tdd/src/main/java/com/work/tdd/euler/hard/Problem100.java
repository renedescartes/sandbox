package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polygonal.isQuadraticSolvable;
import static org.testng.Assert.assertEquals;

public class Problem100 {

    private static final Logger logger = Logger.getLogger(Problem99.class.getName());

    private static BigInteger computeRoot(BigInteger number) {
        return isQuadraticSolvable(BigInteger.valueOf(2), BigInteger.valueOf(-2),
                number.multiply(number.subtract(BigInteger.ONE)).multiply(BigInteger.valueOf(-1)));
    }

    public BigInteger explore(BigInteger start) {
        BigInteger current = start;
        BigInteger root;
        while ((root = computeRoot(current)).compareTo(BigInteger.valueOf(-1)) == 0) {
            logger.fine("Attempting [" + current + "]");
            current = current.add(BigInteger.ONE);
        }
        return root;
    }

    @Test
    public void testSimple() {
        assertEquals(explore(BigInteger.valueOf(1_000_000_000_000L)).intValue(), 15);
    }

    @Test
    public void testBits() {
        assertEquals(explore(BigInteger.valueOf(19)).intValue(), 15);
    }
}
