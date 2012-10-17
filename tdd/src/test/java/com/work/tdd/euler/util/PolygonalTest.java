package com.work.tdd.euler.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PolygonalTest {

    @Test(dataProvider = "numbers-polygonal")
    public void testSimple(long n, int dimension) {
        assertTrue(Polygonal.isPolygonal(n, dimension));
    }

    @Test(dataProvider = "numbers-fail-polygonal")
    public void testFailure(long n, int dimension) {
        assertFalse(Polygonal.isPolygonal(n, dimension));
    }

    @DataProvider(name = "numbers-polygonal")
    public Object[][] data() {
        return new Object[][]{
                {1, 3},
                {3, 3},
                {6, 3},
                {10, 3},
                {15, 3},
                {1, 4},
                {4, 4},
                {9, 4},
                {1, 5},
                {5, 5},
                {12, 5},
                {22, 5},
                {35, 5},
                {1, 6},
                {6, 6},
                {15, 6},
                {28, 6},
                {45, 6},
                {1, 7},
                {7, 7},
                {18, 7},
                {34, 7},
                {55, 7},
                {1, 8},
                {8, 8},
                {21, 8},
                {40, 8},
        };
    }

    @DataProvider(name = "numbers-fail-polygonal")
    public Object[][] failData() {
        return new Object[][]{
                {2, 3},
                {0, 3},
                {-1, 3},
                {9, 3},
                {-1, 4},
                {21, 4},
                {-1, 5},
                {15, 5},
                {-1, 6},
                {17, 6},
                {-1, 7},
                {21, 7},
                {-1, 8},
                {39, 8},
        };
    }
}
