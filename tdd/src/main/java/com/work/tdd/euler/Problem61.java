package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.work.tdd.euler.util.Polygonal;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;
import static com.work.tdd.euler.Utility.summation;
import static com.work.tdd.euler.util.Polygonal.isPolygonal;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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
            if (isPolygonal(n, d) && isNotPolygonal(n, difference(dimensions, newHashSet(d)))) {
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
        Set<Integer> setOfFirstTwoDigits = Sets.newHashSet(Collections2.transform(values, new Function<Integer, Integer>() {
            @Override
            public Integer apply(@Nullable Integer input) {
                return firstTwoDigits(input);
            }
        }));
        Set<Integer> setOfLastTwoDigits = Sets.newHashSet(Collections2.transform(values, new Function<Integer, Integer>() {
            @Override
            public Integer apply(@Nullable Integer input) {
                return lastTwoDigits(input);
            }
        }));
        return difference(setOfFirstTwoDigits, setOfLastTwoDigits).isEmpty() && setOfFirstTwoDigits.size() == values.size();
    }

    @Test
    public void testCyclic() {
        assertFalse(isCyclicSolution(Arrays.asList(1717, 1764, 2145, 2628, 2821, 6426)));
    }

    @Test
    public void testSimple() {
        explore(1010, 9999, Sets.newTreeSet(Arrays.asList(3, 4, 5, 6, 7, 8)), new HashMap<Integer, Integer>());
        logger.info("****Solution****");
        for (Map.Entry<Integer, Integer> entry : solution.entrySet()) {
            logger.info("(" +  entry.getKey() + ", " + Polygonal.polygonalRoot(entry.getValue(), entry.getKey()) + ") -> " + entry.getValue());
        }
        assertEquals(summation(solution.values().toArray(new Integer[solution.values().size()])), new Integer(28684), "Solution is " + solution);
    }
}
