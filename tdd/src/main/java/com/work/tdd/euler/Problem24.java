package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: renedescartes
 * Date: 30/09/12
 */
public class Problem24 {

    public static List<Integer> getPermutation(List<Integer> array, int permutationNumber) {
        List<Integer> inputs = new ArrayList<>(array);
        List<Integer> permutation = new ArrayList<>();
        int permutationIndex = permutationNumber;
        for(int i = inputs.size(); i > 2 ; i--) {
            Integer index = (permutationIndex -1)/ factorial(i - 1);
            permutationIndex = permutationIndex - (index * factorial(i-1));
            createPermutation(permutation, inputs, index);
        }
        createPermutation(permutation, inputs, (permutationIndex -1));
        createPermutation(permutation, inputs, 0);
        return permutation;
    }

    private static void createPermutation(List<Integer> permutation, List<Integer> inputs, int index) {
        Integer element = inputs.get(index);
        permutation.add(element);
        inputs.remove(element);
    }

    private static int factorial(int n) {
        int f = 1;
        for(int i = 2 ; i <= n; i++) {
            f = f * i;
        }
        return f;
    }

    @Test
    public void testSimple() {
        System.out.println(getPermutation(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 1000000));
//        for(int i = 1; i <= 120; i++) {
//            System.out.println(getPermutation(Arrays.asList(1, 2, 3, 4, 5), i));
//        }
    }
}
