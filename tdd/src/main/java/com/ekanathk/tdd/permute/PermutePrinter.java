package com.ekanathk.tdd.permute;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 14/09/12
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class PermutePrinter {

    public static <T> void permute(T[] array, int n) {
        if(n == 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for(int i = 0; i < n; i++) {
            swap(array, i, n-1);
            permute(array, n-1);
            swap(array, i, n-1);
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T buffer = array[i];
        array[i] = array[j];
        array[j] = buffer;
    }

    public static void main(String[] args) {
        permute(new Integer[]{1, 2, 3}, 3);
    }
}
