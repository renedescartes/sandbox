package com.work.tdd.euler.medium;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class Problem51 {
    private static final Logger logger = Logger.getLogger(Problem51.class.getName());

    public static long answer() {
        long count = 13;
        while (true) {
            Integer[] digits = Utility.digits(count);
            logger.info("Count " + count);
            if (Utility.isPrime(count)) {
                for (int i = 1; i < digits.length; i++) {
                    List<List<Integer>> combinations = Utility.combinations(generate(digits.length), i);
                    for (List<Integer> combination : combinations) {
                        Predicate<Long> p = Predicates.and(greaterOrEqual(count), lengthPredicate(digits.length), Utility.primePredicate());
                        Collection<Long> replacements = newArrayList(filter(replacementNumbers(asList(digits), combination), p));
                        if (replacements.size() != 0) {
                            logger.fine(replacements.toString());
                        }
                        if (replacements.size() == 8) {
                            return replacements.iterator().next();
                        }
                    }
                }
            }
            count++;
        }
    }

    private static Predicate<Long> greaterOrEqual(final long count) {
        return new Predicate<Long>() {
            @Override
            public boolean apply(@Nullable Long input) {
                return input >= count;
            }
        };
    }

    private static Predicate<Long> lengthPredicate(final int length) {
        return new Predicate<Long>() {
            @Override
            public boolean apply(@Nullable Long input) {
                return length == input.toString().length();
            }
        };
    }

    private static List<Long> replacementNumbers(List<Integer> digits, List<Integer> positions) {
        List<Long> replacements = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            List<Integer> newDigits = new ArrayList<>(digits);
            for (Integer p : positions) {
                newDigits.set(p, i);
            }
            replacements.add(Long.parseLong(StringUtils.join(newDigits, "")));
        }
        return replacements;
    }

    private static List<Integer> generate(int length) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            positions.add(i);
        }
        return positions;
    }

    @Test
    public void testSimple() {
        System.out.println(answer());
    }

    @Test
    public void testPrevious() {
        assertEquals(generate(5), asList(0, 1, 2, 3, 4));
        assertEquals(replacementNumbers(asList(5, 6, 1, 1, 3), asList(2, 3)),
                asList(56003L, 56113L, 56223L, 56333L, 56443L, 56553L, 56663L, 56773L, 56883L, 56993L));
    }
}
