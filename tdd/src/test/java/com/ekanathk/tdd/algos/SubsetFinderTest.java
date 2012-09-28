package com.ekanathk.tdd.algos;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static com.ekanathk.tdd.algos.SubsetFinder.getCombinations;
import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class SubsetFinderTest {

    private static final Logger logger = Logger.getLogger(SubsetFinderTest.class.getName());

    @Test
    public void testSimple() {
        List<Set<Integer>> combinations = getCombinations(new int[]{1, 2, 3}, 2);
        logger.info("combinations " + combinations);
        assertEquals(3, combinations.size());

        combinations = getCombinations(new int[]{1, 2, 3, 4, 5}, 3);
        logger.info("combinations " + combinations);
        assertEquals(10, combinations.size());
    }
}
