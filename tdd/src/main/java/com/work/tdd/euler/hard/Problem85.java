package com.work.tdd.euler.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Problem85 {

    public long countRectangles(long l, long b, long totalLength, long totalBreadth) {
        return (totalLength - l + 1) * (totalBreadth - b + 1);
    }

    public long totalCount(long totalLength, long totalBreadth) {
        long sum = 0;
        for (long l = 1; l <= totalLength; l++) {
            for (long b = 1; b <= totalBreadth; b++) {
                sum += countRectangles(l, b, totalLength, totalBreadth);
            }
        }
        return sum;
    }

    @DataProvider(name = "rectangles-count")
    protected Object[][] rectangleData() {
        return new Object[][]{
                {1, 1, 3, 2, 6},
                {2, 1, 3, 2, 4},
                {3, 1, 3, 2, 2},
                {1, 2, 3, 2, 3},
                {2, 2, 3, 2, 2},
                {3, 2, 3, 2, 1},
        };
    }

    @Test(dataProvider = "rectangles-count")
    public void testBits(long l, long b, long totalLength, long totalBreadth, long count) {
        assertEquals(countRectangles(l, b, totalLength, totalBreadth), count);
    }

    @Test
    public void testTotalCount() {
        assertEquals(totalCount(3, 2), 18);
    }
}
