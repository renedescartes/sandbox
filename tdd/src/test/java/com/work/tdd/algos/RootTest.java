package com.work.tdd.algos;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class RootTest {

    @DataProvider(name = "numbers")
    public Object[][] createData1() {
        return new Object[][] {
                {0.001, Math.sqrt(0.001)},
                {0.0025, 0.05},
                {64, 8},
                {1, 1},
                {0, 0},
                {0.25, 0.5},
                {2.235, Math.sqrt(2.235)}
        };
    }

    @Test(dataProvider = "numbers")
    public void testSimple(double n, double root) {
       assertEquals(Root.squareRoot(n), root, Root.ERROR);
    }
}
