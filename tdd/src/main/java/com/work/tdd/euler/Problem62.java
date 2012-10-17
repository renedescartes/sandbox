package com.work.tdd.euler;

import com.google.common.base.Predicate;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import static com.google.common.collect.Iterables.filter;
import static com.work.tdd.euler.util.Permutations.permutationIterator;
import static org.testng.Assert.assertEquals;

public class Problem62 {

    private static final Logger logger = Logger.getLogger(Problem62.class.getName());

    private static int countPermutations(Iterable<BigInteger> permutes) {
        int count = 0;
        Set<BigInteger> options = new TreeSet<>();
        for(BigInteger b : permutes) {
            if(isCubicNumber(b.longValue())) {
                options.add(b);
            }
        }
        return options.size();
    }

    public static long findSmallest(int n) {
        long count = 3;
        long permuteCount = 0;
        BigInteger cube = new BigInteger("27");
        while(permuteCount < n) {
            cube = cube(count);
            logger.info("Count = " + count);
            permuteCount = countPermutations(filter(permutationIterator(cube), lengthPredicate(cube.toString().length())));
            count++;
        }
        return cube.longValue();
    }

    private static boolean isCubicNumber(long n) {
        long cbrt = (long) Math.cbrt(n);
        return cbrt * cbrt * cbrt == n;
    }

    private static BigInteger cube(long n) {
        return new BigInteger("" + (n * n * n));
    }

    private static Predicate<BigInteger> lengthPredicate(final int length) {
        return new Predicate<BigInteger>() {
            @Override
            public boolean apply(@Nullable BigInteger input) {
                return length == input.toString().length();
            }
        };
    }

    @Test
    public void testSimple() {
        assertEquals(findSmallest(5), 41063625L);
    }
}
