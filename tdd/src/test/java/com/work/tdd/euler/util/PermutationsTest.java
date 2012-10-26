package com.work.tdd.euler.util;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class PermutationsTest {

    private static final Logger logger = Logger.getLogger(PermutationsTest.class.getName());

    @Test
    public void testSimple() {
        List<List<Integer>> permutes = Lists.newArrayList(Permutations.permutationIterator(asList(1, 2, 3)));
        for (List<Integer> b : permutes) {
            logger.info(b.toString());
        }
        assertEquals(6, permutes.size());

        permutes = Lists.newArrayList(Permutations.permutationIterator(asList(1, 2, 3, 4, 5)));
        for (List<Integer> b : permutes) {
            logger.info(b.toString());
        }
        assertEquals(120, permutes.size());

    }
}
