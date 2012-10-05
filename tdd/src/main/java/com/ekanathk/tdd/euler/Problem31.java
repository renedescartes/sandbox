package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: kannan
 * Date: 05/10/12
 */
public class Problem31 {

    private List<Map<Integer, Integer>> solutions = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Problem31.class.getName());

    public List<Map<Integer, Integer>> computeNumbers(List<Integer> availableWeights, int requiredSum) {
        compute(availableWeights, new TreeMap<Integer, Integer>(), 0, requiredSum);
        return solutions;
    }

    protected void compute(List<Integer> availableWeights, Map<Integer, Integer> chosenWeights, int level, int requiredSum) {
        logger.fine("chosen " + prettyPrint(Arrays.asList(chosenWeights)) + " level " + level);
        if(calculateWeight(chosenWeights) == requiredSum) {
            solutions.add(new TreeMap<>(chosenWeights));
            return;
        }
        if(level == availableWeights.size()) {
            return;
        }
        //One branch where this weight was not added
        compute(availableWeights, new TreeMap<>(chosenWeights), level + 1, requiredSum);
        //now try adding weights to this
        while(calculateWeight(addWeight(chosenWeights, availableWeights.get(level))) <= requiredSum) {
            compute(availableWeights, new TreeMap<>(chosenWeights), level + 1, requiredSum);
        }
    }

    protected static int calculateWeight(Map<Integer, Integer> weights) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    protected static Map<Integer, Integer> addWeight(Map<Integer, Integer> weights, Integer weight) {
        Integer count = weights.get(weight);
        count = (count == null) ? 0 : count;
        weights.put(weight, count + 1);
        return weights;
    }
    
    protected static String prettyPrint(List<Map<Integer, Integer>> solutions) {
        StringBuffer b = new StringBuffer();
        for (Map<Integer, Integer> solution : solutions) {
            for (Map.Entry<Integer, Integer> entry : solution.entrySet()) {
                b.append(entry.getKey()).append("p * ").append(entry.getValue()).append("\t + ");
            }
            if(!solution.entrySet().isEmpty()) {
                b.delete(b.length() -2, b.length());
            }
            b.append("\n");

        }
        return b.toString();
    }

    @Test
    public void testSimple() {
        Problem31 pbm = new Problem31();
        List<Map<Integer, Integer>> solutionsForNum = pbm.computeNumbers(Arrays.asList(200, 100, 50, 20, 10, 5, 2, 1), 200);
        //List<Map<Integer, Integer>> solutionsForNum = pbm.computeNumbers(Arrays.asList(10, 5, 2, 1), 10);
        //System.out.println(prettyPrint(solutionsForNum));
        System.out.println(solutionsForNum.size());//73682
    }
}
