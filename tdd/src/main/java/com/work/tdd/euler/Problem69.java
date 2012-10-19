package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.work.tdd.euler.util.Tuple;
import com.work.tdd.euler.util.fraction.Fraction;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;
import static com.work.tdd.euler.util.fraction.Fractions.longFraction;
import static org.testng.Assert.assertEquals;

public class Problem69 {
    private static final Logger logger = Logger.getLogger(Problem45.class.getName());

    private static final Object[] factors = new Object[1000000];

    @SuppressWarnings("unchecked")
    public static Set<Long> cachedPrimeFactors(Long n) {
        if(factors[n.intValue()] == null) {
            factors[n.intValue()] = fastPrimeFactors(n);
        }
        return (Set<Long>) factors[n.intValue()];
    }

    public static Set<Long> fastPrimeFactors(Long n) {
        if(n == 1) {
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
        Fraction fraction = longFraction(n);
        Fraction one = longFraction(1);
        for(Long l : factors) {
            fraction = fraction.multiply(one.subtract(longFraction(l).reciprocal()));
        }
        Preconditions.checkState(fraction.denominator().longValue() == 1L, "The denominator is not 1");
        return fraction.numerator().longValue();
    }

    public static Tuple<Long, Double> explore(long start, long n) {
        double bestRatio = 0;
        long answer = 0;
        for(long i = start; i <= n; i++) {
            Long phi = phi(i);
            double phiRatio = (double)i/(double)phi;
            if(phiRatio > bestRatio) {
                logger.info("Prefer [" + i + "] with phiRatio [" + phiRatio + "] over [" + answer + "] with phiRatio [" + bestRatio + "]");
                bestRatio = phiRatio;
                answer = i;
            }
            if(i % 1000 == 0) {
                logger.info("i = " + i + " phi = " + phi);
            }
        }
        return new Tuple<>(answer, bestRatio);
    }

    public static Tuple<Long, Double> exploreParallel(final long n, long batchSize) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        long numberOfBatches = (n/batchSize) + (n % batchSize == 0 ? 0 : 1);
        List<ListenableFuture<Tuple<Long, Double>>> jobs = new ArrayList<>();
        for(long b = 0; b < numberOfBatches; b++) {
            final long start = Math.max((b * batchSize) + 1, 2);
            final long end = batchSize * (b + 1);
            ListenableFuture<Tuple<Long, Double>> job = service.submit(new Callable<Tuple<Long, Double>>() {
                @Override
                public Tuple<Long, Double> call() throws Exception {
                    return explore(start, end);
                }
            });
            jobs.add(job);
        }
        List<Tuple<Long, Double>> values = Lists.newArrayList(transform(jobs, futureTransform()));
        Collections.sort(values, new Tuple(1, 2).yComparator());
        return values.get(values.size()-1);
    }

    private static Function<? super Future<Tuple<Long, Double>>, Tuple<Long, Double>> futureTransform() {
        return new Function<Future<Tuple<Long, Double>>, Tuple<Long, Double>>() {
            @Override
            public Tuple<Long, Double> apply(@Nullable Future<Tuple<Long, Double>> input) {
                try {
                    return input.get();
                } catch (Exception e) {
                    throw new RuntimeException("Could not get value");
                }
            }
        };
    }

    @Test
    public void testBits() {
        assertEquals(new Tuple(2310L, 4.8125), explore(2, 10000));
        assertEquals(4.8125, exploreParallel(10000, 100).getY());
    }

    @Test
    public void testSimple() {
        assertEquals(210, explore(2, 1000000));
    }

}
