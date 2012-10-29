package com.work.tdd.euler.medium;

import com.google.common.base.Preconditions;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.work.tdd.euler.util.Permutations;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem68 {

    private static final Logger logger = Logger.getLogger(Problem68.class.getName());

    private static boolean isMagicPentagonSolution(List<Integer> ring) {
        return areAllEqual(sumOfIndexes(ring, 0, 5, 6), sumOfIndexes(ring, 1, 6, 7),
                sumOfIndexes(ring, 2, 7, 8), sumOfIndexes(ring, 3, 8, 9), sumOfIndexes(ring, 4, 9, 5));
    }

    private static int sumOfIndexes(List<Integer> ring, int... indices) {
        int sum = 0;
        for (Integer index : indices) {
            sum += ring.get(index);
        }
        return sum;
    }

    private static Integer joinIndexes(List<Integer> ring, int... indices) {
        StringBuilder b = new StringBuilder();
        for (Integer index : indices) {
            b.append(ring.get(index));
        }
        return Integer.parseInt(b.toString());
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

    private static int getLowestIndex(List<Integer> ring, int... indices) {
        int lowestIndex = indices[0];
        for (int i = 0; i < indices.length; i++) {
            if (ring.get(lowestIndex) > ring.get(indices[i])) {
                lowestIndex = indices[i];
            }
        }
        return lowestIndex;
    }

    private static List<Integer> rotate(List<Integer> ring, int index) {
        List<Integer> rotatedRing = new ArrayList<>();
        for (int i = index; i < ring.size(); i++) {
            rotatedRing.add(ring.get(i));
        }
        for (int i = 0; i < index; i++) {
            rotatedRing.add(ring.get(i));
        }
        return rotatedRing;
    }

    private static BigInteger rotateAndGetArrangement(List<Integer> ring) {
        int lowestIndex = getLowestIndex(ring, 0, 1, 2, 3, 4);
        List<Integer> legs = Arrays.asList(
                joinIndexes(ring, 0, 5, 6),
                joinIndexes(ring, 1, 6, 7),
                joinIndexes(ring, 2, 7, 8),
                joinIndexes(ring, 3, 8, 9),
                joinIndexes(ring, 4, 9, 5)
        );
        List<Integer> rotatedRing = rotate(legs, lowestIndex);
        return new BigInteger(StringUtils.join(rotatedRing, ""));

    }

    public static final BigInteger answer() {
        List<Integer> numbers = Lists.newArrayList(Ranges.closed(1, 10).asSet(DiscreteDomains.integers()));
        Iterable<List<Integer>> permutes = Permutations.permutationIterator(numbers);
        BigInteger maximumNumber = BigInteger.ZERO;
        for (List<Integer> permutation : permutes) {
            if (isMagicPentagonSolution(permutation)) {
                BigInteger solution = rotateAndGetArrangement(permutation);
                logger.info("Arrangement " + permutation + " solution [" + solution + "] best solution [" + maximumNumber + "]");
                Preconditions.checkState(solution.toString().length() == 16 || solution.toString().length() == 17, "Only 16 or 17 digit numbers expected");
                if (solution.toString().length() == 16 && solution.compareTo(maximumNumber) > 0) {
                    logger.info("New solution [" + solution + "]");
                    maximumNumber = solution;
                }
            }
        }
        return maximumNumber;
    }

    @Test
    public void testSimple() {
        assertEquals(answer().toString(), "6531031914842725");
    }

    @Test
    public void testBits() {
        assertEquals(rotateAndGetArrangement(Arrays.asList(2, 3, 4, 5, 1, 10, 7, 9, 6, 8)), new BigInteger("18102107379496568"));
    }
}
