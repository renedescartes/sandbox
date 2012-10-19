package com.work.tdd.euler;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

class Combination {

    private static final Logger logger = Logger.getLogger(Combination.class.getName());
    private List<Integer> array;
    private int finalCost;

    Combination(List<Integer> array, int finalCost) {
        this.array = array;
        this.finalCost = finalCost;
    }

    int explore(int level, int current) {
        logger.info("Level = " + level + " Current = " + current);
        if (current == finalCost) {
            return 1;
        }
        if (level == array.size() || current > finalCost) {
            return 0;
        }
        return explore(level + 1, current) + explore(level + 1, current + array.get(level));
    }

}

public class Problem76New {

    private static final Logger logger = Logger.getLogger(Problem76New.class.getName());

    public static long countCombine(int n) {
        List<Integer> integers = Lists.newArrayList(Ranges.open(0, n).asSet(DiscreteDomains.integers()));
        Combination c = new Combination(integers, n);
        return c.explore(0, 0);
    }

    @Test
    public void testSimple() {
    }

    @Test
    public void testBits() {
        assertEquals(countCombine(6), 10);
        assertEquals(countCombine(10), 41);
    }
}

