package com.work.tdd.euler.util;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureUtil {

    public static <T> List<ListenableFuture<T>> submitParallelJobs(final int n, final int batchSize, final Callable<T> callable) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        long numberOfBatches = (n / batchSize) + (n % batchSize == 0 ? 0 : 1);
        List<ListenableFuture<T>> jobs = new ArrayList<>();
        for (long b = 0; b < numberOfBatches; b++) {
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
        return jobs;
    }

    public static Function<? super Future<Object>,Object> futureTransform() {
        return new Function<Future<Object>,Object>() {

            @Override
            public Object apply(@Nullable Future<Object> input) {
                try {
                    return input == null ? null : input.get();
                } catch (Exception e) {
                    throw new RuntimeException("Could not get value", e);
                }
            }
        };
    }
}
