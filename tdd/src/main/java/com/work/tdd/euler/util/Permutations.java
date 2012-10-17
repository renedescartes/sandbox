package com.work.tdd.euler.util;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

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
}
