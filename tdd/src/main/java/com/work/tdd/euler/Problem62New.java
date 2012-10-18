package com.work.tdd.euler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Problem62New {

    private static final Logger logger = Logger.getLogger(Problem62New.class.getName());

    private static Iterable<Long> nDigitNumbersThatAreCubes(final int digits) {
        final Iterator<Long> iterator = new Iterator<Long>() {
            long current = (long) Math.ceil(Math.cbrt(start(digits)));
            long end = end(digits);
            @Override
            public boolean hasNext() {
                return cube(current) <= end;
            }

            @Override
            public Long next() {
                return cube(current++);
            }

            @Override
            public void remove() {
            }
        };
        return new Iterable<Long>() {

            @Override
            public Iterator<Long> iterator() {
                return iterator;
            }
        };
    }

    private static long cube(long n) {
        return n * n * n;
    }

    private static boolean isCubicNumber(long n) {
        long cubicRoot = (long) Math.cbrt(n);
        return cube(cubicRoot) == n;
    }

    private static Long start(int digits) {
        return (long) Math.pow(10, digits -1);
    }

    private static Long end(int digits) {
        return start(digits+1) - 1;
    }

    private static Long smallestPermutation(Long b) {
        Integer[] digits = Utility.digits(b);
        Arrays.sort(digits);
        return Long.parseLong(StringUtils.join(digits, ""));
    }

    public static List<Collection<Long>> permutesCount(int numberOfPermutes, int digits) {
        List<Long> cubeNumbersList = Lists.newArrayList(nDigitNumbersThatAreCubes(digits));
        Multimap<Long, Long> map = ArrayListMultimap.create();
        List<Collection<Long>> permutes = new ArrayList<>();
        for(Long l : cubeNumbersList) {
            map.put(smallestPermutation(l), l);
        }
        for(Long l : map.keySet()) {
            if(map.get(l).size() >= numberOfPermutes) {
                permutes.add(map.get(l));
            }
        }
        return permutes;
    }

    public static List<Collection<Long>> searchForPermutes(int numberOfPermutes) {
        int digitCount = 3;
        List<Collection<Long>> permutes;
        while((permutes = permutesCount(numberOfPermutes, digitCount)).isEmpty()) {
            digitCount++;
            logger.info("Looking for numbers with [" + digitCount + "] digits");
        }
        return permutes;
    }

    @Test
    public void testSimple() {
        List<Collection<Long>> bestPermutes = searchForPermutes(5);
        logger.info("Permutes " + bestPermutes);
    }

    @Test
    public void testComponent() {
        List<Long> longs = Lists.newArrayList(nDigitNumbersThatAreCubes(3));
        assertEquals(5, longs.size(), longs.toString());
        assertTrue(isCubicNumber(729));
    }

}
