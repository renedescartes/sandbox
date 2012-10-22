package com.work.tdd.euler.util.future;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class FutureUtil {

    private static final Logger logger = Logger.getLogger(FutureUtil.class.getName());

    /**
     * This method helps you split a problem into smaller splittable batched jobs. And provides a list of Future
     * One could then join them together
     */
    public static <T> List<ListenableFuture<T>> submitParallelJobs(final int n, final int batchSize, final BatchCallable<T> callable) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        long numberOfBatches = (n / batchSize) + (n % batchSize == 0 ? 0 : 1);
        logger.log(Level.INFO, "Number of batches {0} batch size {1}", new Object[]{numberOfBatches, batchSize});
        List<ListenableFuture<T>> jobs = new ArrayList<>();
        for (int b = 0; b < numberOfBatches; b++) {
            final int start = Math.max((b * batchSize) + 1, 2);
            final int end = batchSize * (b + 1);
            final int batchNumber = b;
            ListenableFuture<T> job = service.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return callable.batchCall(start, end, batchNumber);
                }
            });
            jobs.add(job);
        }
        return jobs;
    }

    public static <T> Function<Future<T>,T> futureTransform() {
        return new Function<Future<T>,T>() {

            @Override
            public T apply(@Nullable Future<T> input) {
                try {
                    return input == null ? null : input.get();
                } catch (Exception e) {
                    throw new RuntimeException("Could not get value", e);
                }
            }
        };
    }

}
