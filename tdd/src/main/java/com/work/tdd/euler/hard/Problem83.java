package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem83 {

    private static final Logger logger = Logger.getLogger(Problem83.class.getName());

    private static long shortestPathRecursive(Integer[][] array, Integer row, Integer column, Long[][] cache) {
        logger.info("row = " + row + " column = " + column);
        if (cache[row][column] != null) {
            return cache[row][column];
        }
        List<Long> possibleAnswers = new ArrayList<>();
        if (row > 0) {
            possibleAnswers.add(shortestPathRecursive(array, row - 1, column, cache));
        }
        if (row < array.length - 1) {
            possibleAnswers.add(shortestPathRecursive(array, row + 1, column, cache));
        }
        if (column > 0) {
            possibleAnswers.add(shortestPathRecursive(array, row, column - 1, cache));
        }
        if (column < array.length - 1) {
            possibleAnswers.add(shortestPathRecursive(array, row, column + 1, cache));
        }
        Collections.sort(possibleAnswers);
        cache[row][column] = possibleAnswers.get(0);
        return cache[row][column];
    }

    public static long shortestPathRecursive(Integer[][] array) {
        Long[][] cache = new Long[array.length][array.length];
        cache[0][0] = array[0][0].longValue();
        return shortestPathRecursive(array, array.length - 1, array.length - 1, cache);
    }

    @Test
    public void testSimple() {
        Integer[][] array = new Integer[][]{
                {131, 673, 234, 103, 18},
                {201, 96, 342, 965, 150},
                {630, 803, 746, 422, 111},
                {537, 699, 497, 121, 956},
                {805, 732, 524, 37, 331}
        };
        assertEquals(shortestPathRecursive(array), 331);
    }


}
