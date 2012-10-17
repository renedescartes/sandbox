package com.work.tdd.euler;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.work.tdd.euler.util.Polygonal;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.Sets.newHashSet;
import static com.work.tdd.euler.Utility.summation;
import static com.work.tdd.euler.util.Polygonal.isPolygonal;
import static org.testng.Assert.assertEquals;

public class Problem61 {

    private static final Logger logger = Logger.getLogger(Problem61.class.getName());

    private Map<Integer, Integer> solution;

    public void explore(int start, int end, Set<Integer> requiredDimensions, Map<Integer, Integer> completedDimensions) {
        if(requiredDimensions.isEmpty()) {
            if(isCyclicSolution(completedDimensions.values())) {
                solution = Maps.newHashMap(completedDimensions);
            }
            return;
        }
        if(solution != null) {
            return;
        }
        logger.info("start [" + start + "] end [" + end + "] dimensions " + requiredDimensions + " completed " + completedDimensions);
        for(int i = start; i <= end; i++) {
            if(i % 100 >= 10 && (!completedDimensions.values().contains(i))) {
                int d = dimension(i, requiredDimensions);
                if(d != -1) {
                    Set<Integer> newDimensions = Sets.newTreeSet(requiredDimensions);
                    newDimensions.remove(d);
                    Map<Integer,Integer> newDimensionsMap = Maps.newHashMap(completedDimensions);
                    newDimensionsMap.put(d, i);
                    explore(start(i), end(i), newDimensions, newDimensionsMap);
                }
            }
        }
    }

    public static int dimension(long n, Set<Integer> dimensions) {
        for (Integer d : dimensions) {
            if (isPolygonal(n, d) && isNotPolygonal(n, Sets.difference(dimensions, newHashSet(d)))) {
                return d;
            }
        }
        return -1;
    }

    public static boolean isNotPolygonal(long n, Set<Integer> polygonal) {
        for(Integer d : polygonal) {
            if(isPolygonal(n, d)) {
                return false;
            }
        }
        return true;
    }


    /** For 8756 return 5610*/
    private static int start(int number) {
        return (lastTwoDigits(number) * 100) + 10;
    }

    private static int lastTwoDigits(int number) {
        return number % 100;
    }

    private static int firstTwoDigits(int number) {
        return Integer.parseInt(("" + number).substring(0, 2));
    }

    /** For 8756 return 5699*/
    private static int end(int number) {
        return start(number) + 89;
    }

    private static boolean isCyclicSolution(Collection<Integer> values) {
        Set<Integer> integers = Sets.newTreeSet(values);
        Integer first = integers.iterator().next();
        Integer next;
        while( ((next = findNumberStartingWith(integers, lastTwoDigits(first))) != -1) && (!integers.isEmpty())) {
            first = next;
            integers.remove(next);

        }
        return integers.isEmpty();
    }

    private static Integer findNumberStartingWith(Collection<Integer> numbers, int twoDigits) {
        for(Integer n: numbers) {
            if(firstTwoDigits(n) == twoDigits) {
                return n;
            }
        }
        return -1;
    }

    @Test
    public void testSimple() {
        //assertFalse(isCyclicSolution(Arrays.asList(2415, 1024, 1520, 2016, 3367, 1633)));
        explore(1010, 9999, Sets.newTreeSet(Arrays.asList(3, 4, 5, 6, 7, 8)), new HashMap<Integer, Integer>());
        logger.info("****Solution****");
        logger.info("(3, " + Polygonal.isQuadraticSolvable(1, 1, (-2 * solution.get(3))) + ") -> " + solution.get(3));
        logger.info("(4, " + Polygonal.isQuadraticSolvable(1, 0, (-1 * solution.get(4))) + ") -> " + solution.get(4));
        logger.info("(5, " + Polygonal.isQuadraticSolvable(3, -1, (-2 * solution.get(5))) + ") -> " + solution.get(5));
        logger.info("(6, " + Polygonal.isQuadraticSolvable(2, -1, (-1 * solution.get(6))) + ") -> " + solution.get(6));
        logger.info("(7, " + Polygonal.isQuadraticSolvable(5, -3, (-2 * solution.get(7))) + ") -> " + solution.get(7));
        logger.info("(8, " + Polygonal.isQuadraticSolvable(3, -2, (-1 * solution.get(8))) + ") -> " + solution.get(8));
        assertEquals(summation(solution.values().toArray(new Integer[solution.values().size()])), new Integer(11975), "Solution is " + solution);
    }
}
