package com.work.tdd.euler;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polygonal.dimension;

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
        Integer head = first;
        Integer next;
        while( ((next = findNumberStartingWith(integers, lastTwoDigits(first))) != -1) && (integers.size() != 1)) {
            first = next;
            integers.remove(next);

        }
        return integers.size() == 1 && integers.iterator().next() == head;
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
        //assertTrue(isCyclicSolution(Arrays.asList(1176, 1089, 4510, 8911, 7645, 1160)));
        explore(1010, 9999, Sets.newTreeSet(Arrays.asList(3, 4, 5, 6, 7, 8)), new HashMap<Integer, Integer>());
        System.out.println(solution);
    }
}
