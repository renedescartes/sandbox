package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 05/10/12
 */
public class Problem29 {

    private static final Logger logger = Logger.getLogger(Problem29.class.getName());

    public static int countOfPowers(int n) {
        Set<BigInteger> s = new TreeSet<>();
        for(int a = 2; a <= n; a++) {
            for(int b = 2; b <= n; b++) {
                s.add(new BigInteger("" + a).pow(b));
            }
        }
        logger.info(s.toString());
        return s.size();
    }

    @Test
    public void testSimple() {
        assertEquals(countOfPowers(5), 15);
        assertEquals(countOfPowers(100), 9183);
    }

}
