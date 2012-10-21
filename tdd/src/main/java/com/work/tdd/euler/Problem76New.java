package com.work.tdd.euler;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.work.tdd.euler.util.Combinations;

import java.util.List;
import java.util.logging.Logger;

public class Problem76New {

    private static final Logger logger = Logger.getLogger(Problem76New.class.getName());

    public static long f(int n, int blocks) {
        int slots = blocks - 1;
        if (slots == 1) {
            return n / 2;
        }
        List<Integer> elements = Lists.newArrayList(Ranges.closed(1, n).asSet(DiscreteDomains.integers()));
        Iterable<List<Integer>> permutes = Combinations.combinationIterator(elements, slots);
        return 1;
    }
}

