package com.work.tdd.euler;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class Problem62New {

    private static final Logger logger = Logger.getLogger(Problem62New.class.getName());

    private static Iterable<Long> nDigitNumbersThatAreCubes(final int digits) {
        final Iterator<Long> iterator = new Iterator<Long>() {
            long current = start(digits);
            long end = end(digits);
            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Long next() {
                return current++;
            }

            @Override
            public void remove() {
            }
        };
        Iterable<Long> nDigitNumbers = new Iterable<Long>() {

            @Override
            public Iterator<Long> iterator() {
                return iterator;
            }
        };
        return Iterables.filter(nDigitNumbers, cubicPredicate());
    }

    private static Predicate<? super Long> cubicPredicate() {
        return new Predicate<Long>() {
            @Override
            public boolean apply(@Nullable Long input) {
                return isCubicNumber(input);
            }
        };
    }

    private static boolean isCubicNumber(long n) {
        long cbrt = (long) Math.cbrt(n);
        return cbrt * cbrt * cbrt == n;
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

    public static Collection<Long> permutesCount(int n, int digits) {
        List<Long> cubeNumbersList = Lists.newArrayList(nDigitNumbersThatAreCubes(digits));
        Multimap<Long, Long> map = ArrayListMultimap.create();
        for(Long l : cubeNumbersList) {
            map.put(smallestPermutation(l), l);
        }
        for(Long l : map.keySet()) {
            if(map.get(l).size() >= n) {
                return map.get(l);
            }
        }
        return null;
    }

    @Test
    public void testSimple() {
        System.out.println(permutesCount(3, 8));
    }

}
