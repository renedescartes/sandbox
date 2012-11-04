package com.work.tdd.euler.hard;

import com.work.tdd.euler.util.fraction.Continuations;
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

    /**
     * a = 2, b = -2
     *
     * @param MAX
     * @return
     */
    public static BigInteger answer(long MAX) {
        BigInteger b = BigInteger.valueOf((long) Math.sqrt(MAX));
        while (true) {
            logger.info("Inspecting " + b);
            BigInteger product = b.multiply(b.subtract(ONE)).multiply(BigInteger.valueOf(2));
            BigInteger root = Continuations.squareRoot(product, 10).toBigInteger();
            if (isProduct(root, product) || isProduct(root.subtract(ONE), product) || isProduct(root.add(ONE), product)) {
                return b;
            }
            b = b.add(ONE);
        }
    }

    @Test
    public void testSimple() {
        assertEquals(answer(1_000_000_000_000L).toString(), "23");
    }

    @Test
    public void testBits() {
        assertEquals(answer(19).intValue(), 15);
    }
}
