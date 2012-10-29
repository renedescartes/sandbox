package com.work.tdd.euler.medium;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.work.tdd.euler.util.Tuple;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.transform;
import static org.testng.Assert.assertEquals;

public class Problem72 {

    private static final Logger logger = Logger.getLogger(Problem73.class.getName());

    public static Tuple<Long, Long> explore(long start, long n) {
        long sum = 0;
        for (long i = start; i <= n; i++) {
            Long phi = Problem69.phi(i);
            sum += phi;
            if (i % 10000 == 0) {
                logger.info("i = " + i + " phi = " + phi);
            }
        }
        return new Tuple<>(start, sum);
    }

    public static Long exploreParallel(final long n, long batchSize) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        long numberOfBatches = (n / batchSize) + (n % batchSize == 0 ? 0 : 1);
        List<ListenableFuture<Tuple<Long, Long>>> jobs = new ArrayList<>();
        for (long b = 0; b < numberOfBatches; b++) {
            final long start = Math.max((b * batchSize) + 1, 2);
            final long end = batchSize * (b + 1);
            ListenableFuture<Tuple<Long, Long>> job = service.submit(new Callable<Tuple<Long, Long>>() {
                @Override
                public Tuple<Long, Long> call() throws Exception {
                    return explore(start, end);
                }
            });
            jobs.add(job);
        }
        List<Tuple<Long, Long>> values = Lists.newArrayList(transform(jobs, futureTransform()));

        List<Long> countValues = transform(values, Tuple.<Long, Long>yFunction());
        logger.info("Finished jobs " + values);
        return Utility.summation(countValues);
    }

    private static Function<? super Future<Tuple<Long, Long>>, Tuple<Long, Long>> futureTransform() {
        return new Function<Future<Tuple<Long, Long>>, Tuple<Long, Long>>() {
            @Override
            public Tuple<Long, Long> apply(@Nullable Future<Tuple<Long, Long>> input) {
                try {
                    return input == null ? null : input.get();
                } catch (Exception e) {
                    throw new RuntimeException("Could not get value", e);
                }
            }
        };
    }

    public static long countOfFractions(long d) {
        long count = 0;
        for (long den = 2; den <= d; den++) {
            int step = den % 2 == 0 ? 2 : 1;
            for (long num = 1; num < den; num += step) {
                if (Utility.gcd(num, den) == 1) {
                    count++;
                }
            }
            if (den % 1000 == 0) {
                logger.info("Denominator [" + den + "] and count[" + count + "]");
            }
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(exploreParallel(1000000, 10000).longValue(), 7295372);
    }

    @Test
    public void testBits() {
        Long count = countOfFractions(8);
        assertEquals(count.longValue(), 21);
        assertEquals(exploreParallel(8, 2).longValue(), 21);
        count = countOfFractions(50);
        assertEquals(count.longValue(), 773);
        assertEquals(exploreParallel(50, 5).longValue(), 773);
    }
}
