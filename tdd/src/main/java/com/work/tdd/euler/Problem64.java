package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.RootFraction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class Problem64 {
    private static final Logger logger = Logger.getLogger(Problem64.class.getName());

    public static List<Integer> rootFunction(int n) {
        List<Integer> roots = new ArrayList<>();
        RootFraction f = RootFraction.forSquareRoot(n);
        if(Utility.isPerfectSquare(n)) {
            return Arrays.asList((int)Math.sqrt(n));
        }
        do {
            logger.fine("Root fraction " + f);
            int closest = f.closest();
            roots.add(closest);
            f = f.minus(closest).reciprocal().normalise();
        } while(!f.isSolved());
        roots.add(roots.get(0) + f.getRealTermOfNum());
        logger.info("Number [" + n + "] fractions " + roots);
        return roots;
    }

    public static boolean isOddPeriod(int n) {
        return rootFunction(n).size() % 2 == 0;
    }

    public static int numbersWithOddPeriods(int n) {
        int count = 0;
        for(int i = 2; i <= n; i++) {
            if(isOddPeriod(i)) {
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
        return new Object[][] {
                {14, asList(3, 1, 2, 1, 6)},
                {26, asList(5, 10)},
                {23, asList(4, 1, 3, 1, 8)},
                {25, asList(5)},
        };
    }

    @Test(dataProvider = "root-fraction")
    public void testBits(int n, List<Integer> roots) {
        assertEquals(rootFunction(n), roots);
    }

}
