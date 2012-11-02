package com.work.tdd.euler.hard;

import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.work.tdd.euler.util.Permutations;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

import static com.work.tdd.euler.hard.Problem93.Operator.*;
import static com.work.tdd.euler.util.Combinations.combinationIterator;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Problem93 {

    private static final Logger logger = Logger.getLogger(Problem93.class.getName());

    public static String answer() {
        int maxCount = -1;
        List<Integer> answer = Collections.emptyList();
        List<Integer> numbers = Lists.newArrayList(Ranges.closed(0, 9).asSet(DiscreteDomains.integers()));
        for (List<Integer> combination : combinationIterator(numbers, 4)) {
            int currentCount = successiveCountForCombination(combination);
            if (currentCount > maxCount) {
                maxCount = currentCount;
                answer = combination;
                logger.info("Preferring answer " + answer + " with count [" + maxCount + "]");
            }
        }
        Collections.sort(answer);
        logger.info("Answer " + answer + " outputs " + variationsPerCombination(answer));
        return StringUtils.join(answer, "");
    }

    private static int successiveCountForCombination(List<Integer> combination) {
        return countOfSuccessiveNumbers(variationsPerCombination(combination));
    }

    private static int countOfSuccessiveNumbers(Collection<Integer> integers) {
        List<Integer> list = Lists.newArrayList(integers);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != i + 1) {
                return i;
            }

        }
        return -1;
    }

    private static Collection<Integer> variationsPerCombination(List<Integer> combination) {
        Collection<Integer> outputs = new TreeSet<>();
        Iterable<List<Integer>> permutations = Permutations.permutationIterator(combination);
        for (List<Integer> inputs : permutations) {
            for (List<Operator> operators : allPossibleOperatorCombinationsLengthThree()) {
                outputs.addAll(variationsPerCombinationAndOperatorSet(inputs, operators));
            }
        }
        return outputs;
    }

    private static Collection<Integer> variationsPerCombinationAndOperatorSet(List<Integer> inputs, List<Operator> operators) {
        int a = inputs.get(0), b = inputs.get(1), c = inputs.get(2), d = inputs.get(3);
        Operator x = operators.get(0), y = operators.get(1), z = operators.get(2);
        Collection<Integer> answers = Collections2.filter(Sets.newHashSet(asList(
                operate(operate(operate(a, x, b), y, c), z, d), // 1 2 3
                operate(operate(a, x, b), y, operate(c, z, d)), // 1 3 2
                operate(operate(a, x, operate(b, y, c)), z, d), // 2 1 3
                operate(a, x, operate(operate(b, y, c), z, d)), // 2 3 1
                //3 1 2 is same as 1 3 2
                operate(a, x, operate(b, y, operate(c, z, d))) // 3 2 1
        )), positiveNumberFilter());
        logger.fine("Inputs " + inputs + " operators " + operators + " answers " + answers);
        return answers;
    }

    private static Predicate<? super Integer> positiveNumberFilter() {
        return new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input > 0;
            }
        };
    }

    private static Integer operate(Integer op1, Operator operator, Integer op2) {
        if (op1.equals(Integer.MIN_VALUE) || op2.equals(Integer.MIN_VALUE)) {
            return Integer.MIN_VALUE;
        }
        switch (operator) {
            case ADD:
                return op1 + op2;
            case SUBTRACT:
                return op1 - op2;
            case MULTIPLY:
                return op1 * op2;
            case DIVIDE:
                return op2 == 0 || op1 % op2 == 0 ? Integer.MIN_VALUE : op1 / op2;
            default:
                return Integer.MIN_VALUE;
        }
    }

    public static enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private static List<List<Operator>> allPossibleOperatorCombinationsLengthThree() {
        final List<List<Operator>> options = new ArrayList<>();
        Operator[] operators = Operator.values();
        for (Operator operator1 : operators) {
            for (Operator operator2 : operators) {
                for (Operator operator3 : operators) {
                    options.add(asList(operator1, operator2, operator3));
                }
            }
        }
        return options;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(), "5689");
    }

    @Test
    public void testBits() {
        Collection<Integer> outputs = variationsPerCombinationAndOperatorSet(asList(4, 2, 3, 1), asList(MULTIPLY, ADD, SUBTRACT));
        assertTrue(outputs.contains(16));
        assertTrue(outputs.contains(19));
        assertTrue(outputs.contains(10));
        assertEquals(outputs.size(), 3);
        List<List<Operator>> operators = allPossibleOperatorCombinationsLengthThree();
        assertEquals(operators.size(), 64);
        Collection<Integer> integers = variationsPerCombination(asList(1, 2, 3, 4));
        logger.info(integers.toString());
        assertEquals(successiveCountForCombination(asList(1, 2, 3, 4)), 28);
    }
}
