package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CombinationTest {

    @Test
    public void testSimple() {
        Iterable<List<Integer>> lists = Combinations.combinationIterator(Arrays.asList(1, 2, 3, 4, 5), 2);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
