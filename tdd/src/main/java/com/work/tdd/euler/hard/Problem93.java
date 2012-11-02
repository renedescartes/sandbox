package com.work.tdd.euler.hard;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.work.tdd.euler.util.Permutations;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

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
        return countOfSuccessiveNumbers(variationsPerCombination(combination).keySet());
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

    private static Map<Integer, String> variationsPerCombination(List<Integer> combination) {
        Map<Integer, String> outputs = new TreeMap<>();
        Iterable<List<Integer>> permutations = Permutations.permutationIterator(combination);
        for (List<Integer> inputs : permutations) {
            for (List<Operator> operators : allPossibleOperatorCombinationsLengthThree()) {
                outputs.putAll(variationsPerCombinationAndOperatorSet(inputs, operators));
            }
        }
        return outputs;
    }

    private static Map<Integer, String> variationsPerCombinationAndOperatorSet(List<Integer> inputs, List<Operator> operators) {
        int a = inputs.get(0), b = inputs.get(1), c = inputs.get(2), d = inputs.get(3);
        Operator x = operators.get(0), y = operators.get(1), z = operators.get(2);
        Map<Integer, String> outputs = new TreeMap<>();
        Integer value = operate(operate(operate(a, x, b), y, c), z, d); // 1 2 3
        if (value > 0) {
            outputs.put(value, "( ( ( " + a + x + b + " )" + y + c + " ) " + z + d + " ) ");
        }
        value = operate(operate(a, x, b), y, operate(c, z, d)); // 1 3 2
        if (value > 0) {
            outputs.put(value, "( ( " + a + x + b + " )" + y + "( " + c + z + d + " ) )");
        }
        value = operate(operate(a, x, operate(b, y, c)), z, d); // 2 1 3
        if (value > 0) {
            outputs.put(value, "( ( " + a + x + "( " + b + y + c + " ) )" + z + d + ")");
        }
        value = operate(a, x, operate(operate(b, y, c), z, d)); // 2 3 1
        if (value > 0) {
            outputs.put(value, "( " + a + x + "( ( " + b + y + c + " )" + z + d + " ) )");
        }
        value = operate(a, x, operate(b, y, operate(c, z, d))); // 3 2 1
        if (value > 0) {
            outputs.put(value, "( " + a + x + "( " + b + y + "(" + c + z + d + ") ) )");
        }
        logger.fine("Inputs " + inputs + " operators " + operators + " answers " + outputs);
        return outputs;
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
                return op2 == 0 || op1 % op2 != 0 ? Integer.MIN_VALUE : op1 / op2;
            default:
                return Integer.MIN_VALUE;
        }
    }

    public static enum Operator {
        ADD(" + "), SUBTRACT(" - "), MULTIPLY(" * "), DIVIDE(" / ");
        private String s;

        Operator(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
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
        assertEquals(answer(), "1256");
    }

    @Test
    public void testBits() {
        Collection<Integer> outputs = variationsPerCombinationAndOperatorSet(asList(4, 2, 3, 1), asList(MULTIPLY, ADD, SUBTRACT)).keySet();
        assertTrue(outputs.contains(16));
        assertTrue(outputs.contains(19));
        assertTrue(outputs.contains(10));
        assertEquals(outputs.size(), 3);
        List<List<Operator>> operators = allPossibleOperatorCombinationsLengthThree();
        assertEquals(operators.size(), 64);
        logger.info(variationsPerCombination(asList(1, 2, 5, 8)).toString());
        assertEquals(successiveCountForCombination(asList(1, 2, 3, 4)), 28);
    }
}
