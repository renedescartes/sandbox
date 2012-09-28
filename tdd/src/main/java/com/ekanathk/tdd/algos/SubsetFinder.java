package com.ekanathk.tdd.algos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class SubsetFinder {

    /** instance variable to store all combinations*/
    private List<Set<Integer>> solutions = new ArrayList<Set<Integer>>();


    public static List<Set<Integer>> getCombinations(int[] inputs, int n) {
        SubsetFinder f = new SubsetFinder();
        f.visit(inputs, new HashSet<Integer>(), 0, n);
        return f.solutions;
    }

    protected void visit(int[] inputs, Set<Integer> permutes, int current, int expected) {
        /** Have we got enough numbers?*/
        if(permutes.size() == expected) {
            solutions.add(new HashSet<Integer>(permutes));
            return;
        }
        /** Is it end of stack*/
        if(current == inputs.length) {
            return;
        }
        visit(inputs, addElement(permutes, inputs[current]), current + 1, expected);
        visit(inputs, new HashSet<Integer>(permutes), current + 1, expected);
    }

    /** Create a new set which is basically a copy of the given set with the element appended*/
    private static Set<Integer> addElement(Set<Integer> set, Integer n) {
        Set<Integer> elements = new HashSet<Integer>(set);
        elements.add(n);
        return elements;
    }
}
