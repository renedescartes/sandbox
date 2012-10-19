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
    private long count = 0;

    Combination(List<Integer> array, int finalCost) {
        this.array = array;
        this.finalCost = finalCost;
    }

    void explore(int level, int current) {
        logger.fine("Level = " + level + " Current = " + current);
        if (current == finalCost) {
            count++;
            return;
        }
        if (level == array.size() || current > finalCost) {
            return;
        }
        explore(level + 1, current);
        int times = 1;
        int weightToAdd;
        while ((weightToAdd = current + (times * array.get(level))) <= finalCost) {
            explore(level + 1, weightToAdd);
            times++;
        }
    }

    public long getCount() {
        return count;
    }
}

public class Problem76New {

    public static long countCombine(int n) {
        List<Integer> integers = Lists.newArrayList(Ranges.open(0, n).asSet(DiscreteDomains.integers()));
        Combination c = new Combination(integers, n);
        c.explore(0, 0);
        return c.getCount();
    }

    @Test
    public void testSimple() {
        assertEquals(countCombine(100), 10);
    }

    @Test
    public void testBits() {
        assertEquals(countCombine(6), 10);
        assertEquals(countCombine(10), 41);
    }
}

