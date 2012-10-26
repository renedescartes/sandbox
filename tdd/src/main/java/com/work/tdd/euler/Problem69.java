package com.work.tdd.euler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.work.tdd.euler.util.Tuple;
import com.work.tdd.euler.util.fraction.Fraction;
import com.work.tdd.euler.util.future.BatchCallable;
import com.work.tdd.euler.util.future.FutureUtil;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;
import static com.work.tdd.euler.util.fraction.Fractions.longFraction;
import static org.testng.Assert.assertEquals;

public class Problem69 {
    private static final Logger logger = Logger.getLogger(Problem45.class.getName());

    private static final Object[] factors = new Object[10000001];

    @SuppressWarnings("unchecked")
    public static Set<Long> cachedPrimeFactors(Long n) {
        if (factors[n.intValue()] == null) {
            factors[n.intValue()] = fastPrimeFactors(n);
        }
        return (Set<Long>) factors[n.intValue()];
    }

    public static Set<Long> fastPrimeFactors(Long n) {
        if (n == 1) {
            return Collections.emptySet();
        }
        if (n % 2 == 0) {
            Set<Long> factors = newHashSet(cachedPrimeFactors(n / 2));
            factors.add(2L);
            return factors;
        }
        long a;
        for (a = (long) Math.sqrt(n); a <= n; a++) {
            if (Utility.isPerfectSquare(a * a - n)) {
                long b = (long) Math.sqrt(a * a - n);
                if (a + b < n) {
                    Set<Long> factors = newHashSet();
                    factors.addAll(cachedPrimeFactors(a + b));
                    factors.addAll(cachedPrimeFactors(a - b));
                    return factors;
                }
            }
        }
        return newHashSet(n);
    }

    public static long phi(long n) {
        Set<Long> factors = cachedPrimeFactors(n);
        Fraction<Long> fraction = longFraction(n);
        Fraction<Long> one = longFraction(1);
        for (Long l : factors) {
            fraction = fraction.multiply(one.subtract(longFraction(l).reciprocal()));
        }
        Preconditions.checkState(fraction.denominator() == 1L, "The denominator is not 1");
        return fraction.numerator();
    }

    public static Tuple<Long, Double> explore(long start, long n) {
        double bestRatio = 0;
        long answer = 0;
        for (long i = start; i <= n; i++) {
            Long phi = phi(i);
            double phiRatio = (double) i / (double) phi;
            if (phiRatio > bestRatio) {
                logger.fine("Prefer [" + i + "] with phiRatio [" + phiRatio + "] over [" + answer + "] with phiRatio [" + bestRatio + "]");
                bestRatio = phiRatio;
                answer = i;
            }
            if (i % 10000 == 0) {
                logger.info("i = " + i + " phi = " + phi);
            }
        }
        return new Tuple<>(answer, bestRatio);
    }

    public static Tuple<Long, Double> exploreParallel(final long n, long batchSize) {
        List<ListenableFuture<Tuple<Long, Double>>> jobs = FutureUtil.submitParallelJobs((int) n, (int) batchSize, new BatchCallable<Tuple<Long, Double>>() {
            @Override
            public Tuple<Long, Double> batchCall(int start, int end, int batchNumber) {
                return explore(start, end);
            }
        });
        List<Tuple<Long, Double>> values = Lists.newArrayList(transform(jobs, FutureUtil.<Tuple<Long, Double>>futureTransform()));
        logger.info("Finished [" + values.size() + "] jobs");
        Collections.sort(values, Tuple.<Long, Double>yComparator());
        return values.get(values.size() - 1);
    }

    @Test
    public void testBits() {
        logger.info("Prime Factors " + cachedPrimeFactors(2310L));
        assertEquals(480, phi(2310));
        assertEquals(960, phi(4620));
        assertEquals(1920, phi(9240));
        assertEquals(new Tuple<>(2310L, 4.8125), explore(2, 10000));
        assertEquals(4.8125, exploreParallel(10000, 100).getY());
    }

    @Test
    public void testSimple() {
        assertEquals(510510, exploreParallel(1000000, 10000).getX().longValue());
    }

}
