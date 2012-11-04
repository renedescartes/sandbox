package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polygonal.isQuadraticSolvable;
import static java.math.BigInteger.ONE;
import static org.testng.Assert.assertEquals;

public class Problem100 {

    private static final Logger logger = Logger.getLogger(Problem99.class.getName());

    private static BigInteger computeRoot(BigInteger number) {
        return isQuadraticSolvable(BigInteger.valueOf(2), BigInteger.valueOf(-2),
                number.multiply(number.subtract(ONE)).multiply(BigInteger.valueOf(-1)));
    }

    public BigInteger explore(BigInteger start) {
        BigInteger current = start;
        BigInteger root;
        while ((root = computeRoot(current)).compareTo(BigInteger.valueOf(-1)) == 0) {
            logger.fine("Attempting [" + current + "]");
            current = current.add(ONE);
        }
        return root;
    }

    private static boolean isProduct(BigInteger a, BigInteger product) {
        return product.equals(a.multiply(a.subtract(ONE)));
    }

    private static boolean isProduct(long a, long product) {
        return product == a * (a - 1);
    }

    /**
     * a = 2, b = -2
     *
     * @param MAX
     * @return
     */
    public static long answer(long MAX) {
        long b = MAX / 3;
        while (true) {
            if (b % 1000 == 0) {
                logger.info("Inspecting " + b);
            }
            long product = b * (b - 1) * 2;
            long root = (long) Math.sqrt(product);
            if (product > MAX) {
                if (isProduct(root, product) || isProduct(root - 1, product) || isProduct(root + 1, product)) {
                    logger.info("b = " + b + " root " + root);
                    return b;
                }
            }
            b++;
        }
    }

    @Test
    public void testSimple() {
        assertEquals(answer(1_000_000_000_000L), "23");
    }

    @Test
    public void testBits() {
        assertEquals(answer(19), 15);
        BigInteger b = new BigInteger("3312555");
        BigInteger n = new BigInteger("4684660");
        assertEquals(b.multiply(b.subtract(ONE)).multiply(BigInteger.valueOf(2)), n.multiply(n.subtract(ONE)));
        b = new BigInteger("3312555");
        n = new BigInteger("4684660");
        assertEquals(b.multiply(b.subtract(ONE)).multiply(BigInteger.valueOf(2)), n.multiply(n.subtract(ONE)));
    }
}
