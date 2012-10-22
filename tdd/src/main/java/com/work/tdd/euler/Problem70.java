package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.work.tdd.euler.util.Tuple;
import org.testng.annotations.DataProvider;
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
import static com.work.tdd.euler.Utility.gcd;
import static com.work.tdd.euler.Utility.lcm;
import static org.testng.Assert.assertEquals;

public class Problem70 {

    private static Logger logger = Logger.getLogger(Problem70.class.getName());

    public static final int MAX = 10000000;
    private static volatile long[] array = new long[MAX + 1];
    private static volatile long filled = 0;

    public static long cachedPhi(int n) {
        if(array[n] == 0) {
            fill(n, phi(n));
        }
        return array[n];
    }

    public static long phi(int n) {
        if(n == 1) {
            return 1;
        }
        if(n % 2 == 0) {
            int m = n / 2;
            long subPhi = cachedPhi(m);
            return m % 2 == 0 ? 2 * subPhi : subPhi;
        }
        if(array[n] != 0) {
            return array[n];
        }
        Tuple<Integer, Integer> factors = fastFactors(n);
        long answer;
        if (factors.getX() == 1) {
            answer = factors.getY() - 1;
        }
        else  {
            int d = (int) gcd(factors.getX(), factors.getY());
            answer = (cachedPhi(factors.getX()) * cachedPhi(factors.getY()) * d)/ cachedPhi(d);
            long lcm = lcm(factors.getX(), factors.getY());
            if(lcm < Integer.MAX_VALUE) {
                fill((int) lcm, (cachedPhi(factors.getX()) * cachedPhi(factors.getY()))/cachedPhi(d));
            }
        }
        fill(2 * n, n % 2 == 0 ? 2 * answer : answer);
        return answer;
    }

    private static void fill(int position, long value) {
        if(position < array.length) {
            array[position] = value;
            filled++;
        }
    }

    private static Tuple<Integer, Integer> fastFactors(int n) {
        if(n == 1 || n == 2 || n == 3) {
            return new Tuple<>(1, n);
        }
        int a;
        for (a = (int) Math.sqrt(n); a <= n; a++) {
            if (Utility.isPerfectSquare(a * a - n)) {
                int b = (int) Math.sqrt(a * a - n);
                if (a + b < n) {
                    return new Tuple<>(a-b, a+b);
                }
            }
        }
        return new Tuple<>(1, n);
    }

    public static Tuple<Long, Double> explore(int start, int MAX, int batchNum) {
        double bestRatio = Double.MAX_VALUE;
        long bestNumber = -1;
        for(int i = start; i < MAX; i++) {
            long phi = phi(i);
            double currentRatio = (double) i / (double) phi;
            if(currentRatio < bestRatio && isPermute(i, phi)) {
                logger.fine("Number [" + i + "] with phi [" + phi + "] and ratio [" + currentRatio + "] filled [" + filled + "] preferred over [" + bestNumber + "] with ratio [" + bestRatio + "]");
                bestRatio = currentRatio;
                bestNumber = i;
            }
        }
        Tuple<Long, Double> tup = new Tuple<>(bestNumber, bestRatio);
        logger.info("Batch [" + batchNum + "] completed tuple [" + tup + "] filled [" + filled + "]");
        return tup;
    }

    public static Tuple<Long, Double> exploreParallel(final int n, int batchSize) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        long numberOfBatches = (n / batchSize) + (n % batchSize == 0 ? 0 : 1);
        List<ListenableFuture<Tuple<Long, Double>>> jobs = new ArrayList<>();
        for (int b = 0; b < numberOfBatches; b++) {
            final int batchNum = b;
            final int start = Math.max((b * batchSize) + 1, 2);
            final int end = batchSize * (b + 1);
            ListenableFuture<Tuple<Long, Double>> job = service.submit(new Callable<Tuple<Long, Double>>() {
                @Override
                public Tuple<Long, Double> call() throws Exception {
                    return explore(start, end, batchNum);
                }
            });
            jobs.add(job);
        }
        List<Tuple<Long, Double>> values = Lists.newArrayList(transform(jobs, futureTransform()));
        logger.info("Finished [" + values + "] jobs");
        Collections.sort(values, new Tuple(1, 2).yComparator());
        return values.get(values.size() - 1);
    }

    public static boolean isPermute(long n1, long n2) {
        Integer[] digits1 = Utility.digits(n1);
        Integer[] digits2 = Utility.digits(n2);
        Arrays.sort(digits1);
        Arrays.sort(digits2);
        return Arrays.equals(digits1, digits2);
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

    @Test
    public void testSimple() {
        assertEquals(exploreParallel(MAX, 50000).getX().longValue(), 25);
    }

    @DataProvider(name = "phi-data-min")
    public Object[][] testData() {
        return new Object[][] {
                {20617, 1.0218576526566217d},
                {45421, 1.0204214593817398d},
                {69271, 1.020011190952998d},
        };
    }

    @Test(dataProvider = "phi-data-min")
    public void testBits(int n, double expected) {
        assertEquals((double) n / (double) (phi(n)), expected, 0.00001d);
    }
}
