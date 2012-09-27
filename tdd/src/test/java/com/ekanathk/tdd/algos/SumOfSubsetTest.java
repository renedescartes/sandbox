package com.ekanathk.tdd.algos;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ekanathk.tdd.algos.SumOfSubsets.sumOfSubsets;
import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: kannan
 * Date: 23/09/12
 * Time: 01:27
 * To change this template use File | Settings | File Templates.
 */
public class SumOfSubsetTest {

    @Test
    public void testSimple() {
        List<Integer>  inputs = Arrays.asList(-1, 2, 3, 5, 8, 9, 10);
        List<List<Boolean>> sums = sumOfSubsets(inputs, 14);
        for(List<Boolean> answer : sums) {
            for(int i = 0; i < answer.size(); i++) {
                if(answer.get(i)) {
                    System.out.print(inputs.get(i) + " ");
                }
            }
            System.out.println();
        }
    }
}
