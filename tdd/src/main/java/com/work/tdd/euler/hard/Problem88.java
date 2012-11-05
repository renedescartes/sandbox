package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import com.work.tdd.euler.util.Polygonal;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class Triple {
    int product;
    int sum;
    int numberOfTerms;

    Triple(int product, int sum, int numberOfTerms) {
        this.product = product;
        this.sum = sum;
        this.numberOfTerms = numberOfTerms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triple triple = (Triple) o;

        if (numberOfTerms != triple.numberOfTerms) return false;
        if (product != triple.product) return false;
        if (sum != triple.sum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sum;
        result = 31 * result + product;
        result = 31 * result + numberOfTerms;
        return result;
    }
}

public class Problem88 {

    private static final Logger logger = Logger.getLogger(Problem88.class.getName());

    private static Integer[][] divisors = new Integer[100000][];

    private static Map<Triple, Boolean> cache = new HashMap<>();

    public static int kthMinimalNumber(int k) {
        return kthMinimalNumber(k, k);
    }

    public static int kthMinimalNumber(int k, int start) {
        for (int current = start; current <= 2 * k; current++) {
            if (cachedCanBeSplit(current, current, k)) {
                return current;
            }
        }
        return -1;
    }

    public static long answer(int MAX) {
        Set<Integer> numbers = new HashSet<>();
        int previousMinimal = 4;
        numbers.add(4);
        for (int k = 3; k <= MAX; k++) {
            int minimal = kthMinimalNumber(k, previousMinimal);
            if (k % 100 == 0) {
                logger.info("K = " + k + " value = " + minimal);
            }
            numbers.add(minimal);
            previousMinimal = minimal;
        }
        return Utility.summation(numbers.toArray(new Integer[numbers.size()]));
    }

    @Test
    public void testSimple() {
        assertEquals(answer(12000), 234);
    }

    @Test
    public void testBits() {
        assertTrue(canBeSplit(12, 12, 6));
        assertEquals(kthMinimalNumber(6), 12);
        assertEquals(kthMinimalNumber(7), 12);
        assertEquals(kthMinimalNumber(8), 12);
        assertEquals(kthMinimalNumber(9), 15);
        assertEquals(kthMinimalNumber(10), 16);
        assertEquals(kthMinimalNumber(11), 16);
        assertEquals(kthMinimalNumber(12), 16);
    }

    private static boolean cachedCanBeSplit(int product, int sum, int numberOfTerms) {
        Triple triple = new Triple(product, sum, numberOfTerms);
        if (!cache.containsKey(triple)) {
            cache.put(triple, canBeSplit(product, sum, numberOfTerms));
        }
        return cache.get(triple);
    }

    private static boolean canBeSplit(int product, int sum, int numberOfTerms) {
        logger.fine("product = [" + product + "] sum [" + sum + "] numberOfTerms [" + numberOfTerms + "]");
        //case of all 1s
        if (product == 1) {
            return sum == numberOfTerms;
        }
        if (numberOfTerms == 1) {
            return sum == product;
        }
        if (sum <= 0 || product <= 0 || numberOfTerms <= 0 || sum < numberOfTerms) {
            return false;
        }
        //two terms
        if (numberOfTerms == 2) {
            return Polygonal.isQuadraticSolvable(1, sum, product) > 0;
        }
        //equal split
        if (sum % numberOfTerms == 0 && Math.pow(sum / numberOfTerms, numberOfTerms) == product) {
            return true;
        }
        Integer[] divisors = cachedDivisors(product);
        for (int numberOfOnes = numberOfTerms - 1; numberOfOnes >= 1; numberOfOnes--) {
            for (Integer divisor : divisors) {
                int newProduct = product / divisor;
                int newSum = sum - numberOfOnes - divisor;
                int newNumberOfTerms = numberOfTerms - numberOfOnes - 1;
                if (divisor != 1 && cachedCanBeSplit(newProduct, newSum, newNumberOfTerms)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Integer[] cachedDivisors(Integer n) {
        if (divisors[n] == null) {
            divisors[n] = Utility.properDivisors(n);
        }
        return divisors[n];
    }

}
