package com.work.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.work.tdd.euler.util.fraction.Continuations.continuedFractions;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class Problem64 {
    public static boolean isOddPeriod(int n) {
        return continuedFractions(n).size() % 2 == 0;
    }

    public static int numbersWithOddPeriods(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isOddPeriod(i)) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(numbersWithOddPeriods(13), 4);
        assertEquals(numbersWithOddPeriods(10000), 1322);
    }

    @DataProvider(name = "root-fraction")
    public Object[][] rootFractionData() {
        return new Object[][]{
                {14, asList(3, 1, 2, 1, 6)},
                {26, asList(5, 10)},
                {23, asList(4, 1, 3, 1, 8)},
                {25, asList(5)},
        };
    }

    @Test(dataProvider = "root-fraction")
    public void testBits(int n, List<Integer> roots) {
        assertEquals(continuedFractions(n), roots);
    }

}
