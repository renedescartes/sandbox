package com.work.tdd.euler.hard;

import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.work.tdd.euler.hard.Problem93.Operator.*;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Problem93 {

    private static Collection<Integer> variationsPerCombination(Collection<Integer> combination) {
        return null;
    }

    private static Collection<Integer> variationsPerCombinationAndOperatorSet(List<Integer> inputs, List<Operator> operators) {
        int a = inputs.get(0), b = inputs.get(1), c = inputs.get(2), d = inputs.get(3);
        Operator x = operators.get(0), y = operators.get(1), z = operators.get(2);
        return Sets.newHashSet(asList(
                operate(operate(operate(a, x, b), y, c), z, d), // 1 2 3
                operate(operate(a, x, b), y, operate(c, z, d)), // 1 3 2
                operate(operate(a, x, operate(b, y, c)), z, d), // 2 1 3
                operate(a, x, operate(operate(b, y, c), z, d)), // 2 3 1
                //3 1 2 is same as 1 3 2
                operate(a, x, operate(b, y, operate(c, z, d))) // 3 2 1
        ));
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
                return op1 % op2 == 0 ? Integer.MIN_VALUE : op1 / op2;
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
    public void testBits() {
        Collection<Integer> outputs = variationsPerCombinationAndOperatorSet(asList(4, 2, 3, 1), asList(MULTIPLY, ADD, SUBTRACT));
        assertTrue(outputs.contains(16));
        assertTrue(outputs.contains(19));
        assertTrue(outputs.contains(10));
        assertEquals(outputs.size(), 3);
        List<List<Operator>> operators = allPossibleOperatorCombinationsLengthThree();
        assertEquals(operators.size(), 64);
    }
}
