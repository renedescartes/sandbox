package com.work.tdd.euler.util;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class PermutationsTest {

    private static final Logger logger = Logger.getLogger(PermutationsTest.class.getName());

    @Test
    public void testSimple() {
        List<BigInteger> permutes = Lists.newArrayList(Permutations.permutationIterator(new BigInteger("123")));
        for(BigInteger b : permutes) {
            logger.info(b.toString());
        }
        assertEquals(6, permutes.size());

        permutes = Lists.newArrayList(Permutations.permutationIterator(new BigInteger("12345")));
        for(BigInteger b : permutes) {
            logger.info(b.toString());
        }
        assertEquals(120, permutes.size());
    }
}
