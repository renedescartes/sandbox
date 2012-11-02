package com.work.tdd.euler.hard;

import com.work.tdd.euler.util.Polygonal;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem94 {

    private static final Logger logger = Logger.getLogger(Problem94.class.getName());

    public BigInteger answer(long MAX) {
        BigInteger answer = BigInteger.ZERO;
        long count = 3;
        while (count < MAX) {
            long root = Polygonal.isQuadraticSolvable(3, -2, -1 * ((count * count) + 1));
            if (root != -1 && ((count * (root + 1)) % 4 == 0)) {
                logger.info("Up solution " + root + ", " + root + ", " + (root + 1));
                long perimeter = (3 * root) + 1;
                if (perimeter < MAX) {
                    answer = answer.add(BigInteger.valueOf(perimeter));
                }
            }
            root = Polygonal.isQuadraticSolvable(3, 2, -1 * ((count * count) + 1));
            if (root != -1 && ((count * (root - 1)) % 4 == 0)) {
                logger.info("Down solution " + root + ", " + root + ", " + (root - 1));
                long perimeter = (3 * root) - 1;
                if (perimeter < MAX) {
                    answer = answer.add(BigInteger.valueOf(perimeter));
                }
            }
            count++;
        }
        return answer;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(1_000_000_000).toString(), "518408346");
    }

    @Test
    public void testBits() {
        assertEquals(answer(1000).toString(), "984");
    }
}
