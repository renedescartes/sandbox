package com.work.tdd.euler.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polygonal.approximateRoot;
import static com.work.tdd.euler.util.Polygonal.triangleTerm;
import static org.testng.Assert.assertEquals;

public class Problem85 {

    private static final Logger logger = Logger.getLogger(Problem85.class.getName());

    public static long rectangleCount(long l, long b) {
        return triangleTerm(l) * triangleTerm(b);
    }

    public static long answer(long MAX) {
        long maxLength = 2 * approximateRoot(1, 1, -2 * MAX);
        long bestDifference = Long.MAX_VALUE;
        long bestLength = -1, bestBreadth = -1;
        for (int l = 1; l <= maxLength; l++) {
            for (int b = 1; b <= l; b++) {
                long currentDifference = Math.abs(MAX - rectangleCount(l, b));
                if (currentDifference < bestDifference) {
                    logger.info("l = " + l + " b = " + b + " difference = " + currentDifference);
                    bestBreadth = b;
                    bestLength = l;
                    bestDifference = currentDifference;
                }
            }
        }
        return bestBreadth * bestLength;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(2000000), 2772);
    }


    @DataProvider(name = "rectangles-total-count")
    protected Object[][] rectangleTotalData() {
        return new Object[][]{
                {3, 2, 18},
                {4, 1, 10},
                {5, 2, 45},
                {6, 4, 210},
                {14, 5, 1575},
                {21, 17, 35343},
        };
    }

    @Test(dataProvider = "rectangles-total-count")
    public void testTotalCount(long l, long b, long count) {
        assertEquals(rectangleCount(l, b), count);
    }

    @Test
    public void testBits() {
        assertEquals(approximateRoot(1, 1, -2 * 2000000), 2000);
        assertEquals(triangleTerm(2000), 2001000);
    }
}
