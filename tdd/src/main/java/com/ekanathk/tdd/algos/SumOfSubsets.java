package com.ekanathk.tdd.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kannan
 * Date: 23/09/12
 * Time: 00:58
 * To change this template use File | Settings | File Templates.
 */
public class SumOfSubsets {

    private List<List<Boolean>> solutions = new ArrayList<List<Boolean>>();
    private static final Logger logger = Logger.getLogger(SumOfSubsets.class.getName());

    public static List<List<Boolean>> sumOfSubsets(List<Integer> inputs, Integer sum) {
        SumOfSubsets s = new SumOfSubsets();
        Collections.sort(inputs);
        s.sumOfSubsets(inputs, fill(new ArrayList<Boolean>(), false, inputs.size()), 0, 0, sum);
        return s.solutions;
    }

    private void sumOfSubsets(List<Integer> inputs, List<Boolean> subsetInclusion, int currentSum, int pointer, int totalSum) {
        logger.info("Computing " + inputs + " weights " + subsetInclusion + " currentSum " + currentSum  + " pointer " + pointer + " totalSum " + totalSum);
        if(pointer == inputs.size()) {
            return;
        }
        subsetInclusion.set(pointer, true);
        if(currentSum + inputs.get(pointer) == totalSum) {
            logger.info("Found solution");
            solutions.add(new ArrayList<Boolean>(subsetInclusion));
            return;
        }
        if(currentSum + inputs.get(pointer) < totalSum) {
            sumOfSubsets(inputs, subsetInclusion, currentSum + inputs.get(pointer), pointer + 1, totalSum);
        }
        subsetInclusion.set(pointer, false);
        sumOfSubsets(inputs, subsetInclusion, currentSum, pointer + 1, totalSum);
    }

    private static <T> List<T> fill(List<T> list, T value, int count) {
        for(int i = 0; i< count; i++) {
            list.add(value);
        }
        return list;
    }
}
