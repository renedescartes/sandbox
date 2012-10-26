package com.work.tdd.euler;

import com.google.common.collect.Sets;
import com.work.tdd.euler.util.NumberUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Problem77 {

    private static Map<Long, Collection<Long>> cacheMap = new HashMap<>();
    private static Map<Long, Long> partitionMap = new HashMap<>();

    public static long sumOfPrimeFactors(long n) {
        if (!cacheMap.containsKey(n)) {
            cacheMap.put(n, Sets.newHashSet(NumberUtil.primeFactors(n)));
        }
        return NumberUtil.summation(cacheMap.get(n));
    }

    public static long cachedPrimePartition(long n) {
        if (!partitionMap.containsKey(n)) {
            partitionMap.put(n, primePartition(n));
        }
        return partitionMap.get(n);
    }

    public static long primePartition(long n) {
        if (n == 1) {
            return 0;
        }
        long sum = sumOfPrimeFactors(n);
        for (int j = 1; j < n; j++) {
            sum += sumOfPrimeFactors(j) * cachedPrimePartition(n - j);
        }
        return (sum / n);
    }

    public static long findNumber(long expectedNumberOfPartitions) {
        int count = 3;
        while (cachedPrimePartition(count++) < expectedNumberOfPartitions) ;
        return count - 1;
    }

    @Test
    public void testSimple() {
        assertEquals(findNumber(5), 10);
        assertEquals(findNumber(5000), 71);
    }

    @DataProvider(name = "prime-partition")
    public Object[][] primePartitionData() {
        return new Object[][]{
                {2, 1},
                {3, 1},
                {4, 1},
                {5, 2},
                {6, 2},
                {7, 3},
                {8, 3},
                {9, 4},
                {10, 5},
        };
    }

    @Test(dataProvider = "prime-partition")
    public void testBits(int n, int partitions) {
        assertEquals(primePartition(n), partitions);
    }

    @DataProvider(name = "sopf")
    public Object[][] sopfData() {
        return new Object[][]{
                {1, 0},
                {2, 2},
                {3, 3},
                {4, 2},
                {8, 2},
        };
    }

    @Test(dataProvider = "sopf")
    public void testPrimeFactors(int n, int sopf) {
        assertEquals(sumOfPrimeFactors(n), sopf);
    }
}
