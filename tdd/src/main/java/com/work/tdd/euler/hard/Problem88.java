package com.work.tdd.euler.hard;

import com.google.common.collect.Lists;
import com.work.tdd.euler.medium.Problem76;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Problem88 {

    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static Set<Integer> productSumSets(int n) {
        Set<Integer> listOfKs = new HashSet<>();
        int level = 1;
        Set<List<Integer>> permutes = new HashSet<>();
        permutes.add(Arrays.asList(n));
        Set<List<Integer>> newPermutes = new HashSet<>();
        while (level <= n) {
            logger.info("Level [" + level + "] stepCount [" + permutes.size() + "] options " + permutes);
            for (List<Integer> digits : permutes) {
                for (int i = 0; i < digits.size(); i++) {
                    if (digits.get(i) != 1) {
                        for (int j = 1; j < digits.get(i); j++) {
                            List<Integer> newAttempt = Lists.newArrayList(digits.subList(0, i));
                            newAttempt.add(j);
                            newAttempt.add(digits.get(i) - j);
                            newAttempt.addAll(digits.subList(i + 1, digits.size()));
                            newPermutes.add(Lists.reverse(newAttempt));
                        }
                    }
                }
            }
            permutes = newPermutes;
            newPermutes = new HashSet<>();
            level++;
        }
        return listOfKs;
    }
}
