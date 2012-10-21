package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.work.tdd.euler.util.Tuple;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.transform;
import static com.work.tdd.euler.Problem69.phi;
import static org.testng.Assert.assertEquals;

public class Problem70 {

    private static Logger logger = Logger.getLogger(Problem70.class.getName());

    public static Tuple<Long, Double> exploreSerial(long batchNum, long start, long max) {
        double phiRatio = Double.MAX_VALUE;
        long answer = 0;
        for (long i = start; i < max; i++) {
            long phi = phi(i);
            if (isPermute(i, phi)) {
                double ratio = (double) i / (double) phi;
                if (ratio < phiRatio) {
                    logger.fine("Batch [" + batchNum + "] Number [" + i + "] and phi[" + phi + "] preferred over answer[" + answer + "] with ratio [" + phiRatio + "]");
                    phiRatio = ratio;
                    answer = i;
                }
            }
        }
        logger.info("Batch [" + batchNum + "] completed");
        return new Tuple<>(answer, phiRatio);
    }

    public static Tuple<Long, Double> exploreParallel(final long n, long batchSize) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        long numberOfBatches = (n / batchSize) + (n % batchSize == 0 ? 0 : 1);
        List<ListenableFuture<Tuple<Long, Double>>> jobs = new ArrayList<>();
        for (long b = 0; b < numberOfBatches; b++) {
            final long batchNum = b;
            final long start = Math.max((b * batchSize) + 1, 2);
            final long end = batchSize * (b + 1);
            ListenableFuture<Tuple<Long, Double>> job = service.submit(new Callable<Tuple<Long, Double>>() {
                @Override
                public Tuple<Long, Double> call() throws Exception {
                    return exploreSerial(batchNum, start, end);
                }
            });
            jobs.add(job);
        }
        List<Tuple<Long, Double>> values = Lists.newArrayList(transform(jobs, futureTransform()));
        logger.info("Finished [" + values + "] jobs");
        Collections.sort(values, new Tuple(1, 2).yComparator());
        return values.get(0);
    }

    private static Function<? super Future<Tuple<Long, Double>>, Tuple<Long, Double>> futureTransform() {
        return new Function<Future<Tuple<Long, Double>>, Tuple<Long, Double>>() {
            @Override
            public Tuple<Long, Double> apply(@Nullable Future<Tuple<Long, Double>> input) {
                try {
                    return input == null ? null : input.get();
                } catch (Exception e) {
                    throw new RuntimeException("Could not get value", e);
                }
            }
        };
    }

    private static boolean isPermute(long n1, long n2) {
        Integer[] d1 = Utility.digits(n1);
        Arrays.sort(d1);
        Integer[] d2 = Utility.digits(n2);
        Arrays.sort(d2);
        return Arrays.equals(d1, d2);
    }

    @Test
    public void testSimple() {
        assertEquals(exploreParallel(10000000, 100000), 75841);
    }
}
