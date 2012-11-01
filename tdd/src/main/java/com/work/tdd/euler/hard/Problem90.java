package com.work.tdd.euler.hard;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.work.tdd.euler.medium.Problem76;
import com.work.tdd.euler.util.Combinations;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem90 {

    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static long answer() {
        List<Integer> elements = Lists.newArrayList(Ranges.closed(0, 9).asSet(DiscreteDomains.integers()));
        Iterable<List<Integer>> dice1Options = Combinations.combinationIterator(elements, 6);
        long sum = 0;
        for (List<Integer> dice1 : dice1Options) {
            Iterable<List<Integer>> dice2Options = Combinations.combinationIterator(elements, 6);
            for (List<Integer> dice2 : dice2Options) {
                logger.fine("Checking " + dice1 + " and " + dice2);
                if (isCompatibleOption(dice1, dice2)) {
                    logger.fine("Answer " + dice1 + " and " + dice2);
                    sum++;
                }
            }
        }
        return sum / 2; //as we would have accounted stuff twice
    }

    private static boolean isCompatibleOption(List<Integer> dice1, List<Integer> dice2) {
        return isCompatibleOption(dice1, dice2, 0, 1) &&
                isCompatibleOption(dice1, dice2, 0, 4) &&
                isCompatibleOption(dice1, dice2, 0, 9) &&
                isCompatibleOption(dice1, dice2, 1, 6) &&
                isCompatibleOption(dice1, dice2, 2, 5) &&
                isCompatibleOption(dice1, dice2, 3, 6) &&
                isCompatibleOption(dice1, dice2, 4, 9) &&
                isCompatibleOption(dice1, dice2, 6, 4) &&
                isCompatibleOption(dice1, dice2, 8, 1);

    }

    private static boolean isCompatibleOption(List<Integer> dice1, List<Integer> dice2, int one, int two) {
        return (doesContain(dice1, one) && doesContain(dice2, two)) || (doesContain(dice2, one) && doesContain(dice1, two));
    }

    private static boolean doesContain(List<Integer> options, Integer n) {
        if (n == 6 || n == 9) {
            return options.contains(6) || options.contains(9);
        } else {
            return options.contains(n);
        }
    }

    @Test
    public void testSimple() {
        assertEquals(answer(), 1217);
    }

    @Test
    public void testBits() {
        List<Integer> elements = Lists.newArrayList(Ranges.closed(0, 9).asSet(DiscreteDomains.integers()));
        Iterable<List<Integer>> dice1Options = Combinations.combinationIterator(elements, 6);
        long sum = 0;
        for (List<Integer> dice1 : dice1Options) {
            Iterable<List<Integer>> dice2Options = Combinations.combinationIterator(elements, 6);
            for (List<Integer> dice2 : dice2Options) {
                sum++;
            }
        }
        assertEquals(sum, 210 * 210);
    }
}
