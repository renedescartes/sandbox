package com.work.tdd.euler;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.work.tdd.euler.util.Permutations;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem68 {

    private static final Logger logger = Logger.getLogger(Problem68.class.getName());

    private static boolean isMagicPentagonSolution(List<Integer> ring) {
        return isSorted(ring.subList(0, 5)) &&
                areAllEqual(sumOfIndexes(ring, 0, 5, 6), sumOfIndexes(ring, 1, 6, 7),
                        sumOfIndexes(ring, 2, 7, 8), sumOfIndexes(ring, 3, 8, 9), sumOfIndexes(ring, 4, 9, 5));
    }

    private static int sumOfIndexes(List<Integer> ring, int... indices) {
        int sum = 0;
        for (Integer index : indices) {
            sum += ring.get(index);
        }
        return sum;
    }

    private static boolean areAllEqual(int... numbers) {
        int first = numbers[0];
        for (int number : numbers) {
            if (first != number) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger getArrangement(List<Integer> ring) {
        return new BigInteger(StringUtils.join(new Integer[]{
                ring.get(0),
                ring.get(5),
                ring.get(6),
                ring.get(1),
                ring.get(6),
                ring.get(7),
                ring.get(2),
                ring.get(7),
                ring.get(8),
                ring.get(3),
                ring.get(8),
                ring.get(9),
                ring.get(4),
                ring.get(9),
                ring.get(5),
        }, ""));
    }

    public static final BigInteger answer() {
        List<Integer> numbers = Lists.newArrayList(Ranges.closed(1, 10).asSet(DiscreteDomains.integers()));
        Iterable<List<Integer>> permutes = Permutations.permutationIterator(numbers);
        BigInteger maximumNumber = BigInteger.ZERO;
        for (List<Integer> permutation : permutes) {
            if (isMagicPentagonSolution(permutation)) {
                BigInteger solution = getArrangement(permutation);
                if (solution.toString().length() == 16 && solution.compareTo(maximumNumber) > 0) {
                    logger.info("Arrangement " + permutation + " solution [" + solution + "]");
                    maximumNumber = solution;
                }
            }
        }
        return maximumNumber;
    }

    @Test
    public void testSimple() {
        assertEquals(answer().toString(), "123123");
    }
}
