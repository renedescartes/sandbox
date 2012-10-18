package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Problem62 {

    private static final Logger logger = Logger.getLogger(Problem62.class.getName());

    /** This method returns a simple iterator that iterates over the cubes that are of N- Digits length*/
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

    /**
     * Returns a Long that is the smallest permutation of the digits in that long
     * ex: 71235 will return 12357
     */
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
                permutes.add(new TreeSet<>(map.get(l)));
            }
        }
        return permutes;
    }

    public static Long searchForPermutes(int numberOfPermutes) {
        int digitCount = 3;
        List<Collection<Long>> permutes;
        while((permutes = permutesCount(numberOfPermutes, digitCount)).isEmpty()) {
            digitCount++;
            logger.info("Looking for numbers with [" + digitCount + "] digits");
        }
        logger.info("Sets of permutations that are cubes " + permutes);
        Set<Long> firstElements = Sets.newTreeSet(Lists.transform(permutes, new Function<Collection<Long>, Long>() {
            @Override
            public Long apply(@Nullable Collection<Long> input) {
                return input.iterator().next();
            }
        }));
        return firstElements.iterator().next();
    }

    @Test
    public void testSimple() {
        Long bestPermute = searchForPermutes(5);
        logger.info("Permutes " + bestPermute);
        assertEquals(127035954683L, bestPermute.longValue());
    }

    @Test
    public void testComponent() {
        List<Long> longs = Lists.newArrayList(nDigitNumbersThatAreCubes(3));
        assertEquals(5, longs.size(), longs.toString());
        assertTrue(isCubicNumber(729));
    }

}
