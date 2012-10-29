package com.work.tdd.euler.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Polygonal.triangleTerm;
import static org.testng.Assert.assertEquals;

public class Problem85 {

    public long rectangleCount(long l, long b) {
        return triangleTerm(l) * triangleTerm(b);
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
}
