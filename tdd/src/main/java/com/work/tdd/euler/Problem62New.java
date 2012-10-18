package com.work.tdd.euler;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class Problem62New {

    private static final Logger logger = Logger.getLogger(Problem62New.class.getName());

    private static Iterable<Long> nDigitNumbersThatAreCubes(final int digits) {
        Iterator<Long> iterator = new Iterator<Long>() {
            @Override
            public boolean hasNext() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Long next() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void remove() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Collection<Long> numbers = Ranges.open(start(digits), end(digits)).asSet(DiscreteDomains.longs());
        return Collections2.filter(numbers, cubicPredicate());
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

    @Test
    public void testSimple() {
        Iterable<Long> x = nDigitNumbersThatAreCubes(11);
        for(Long y : x) {
            System.out.println(y);
        }

    }
}
