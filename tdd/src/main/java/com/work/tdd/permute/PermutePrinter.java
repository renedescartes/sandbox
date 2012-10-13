package com.work.tdd.permute;

import com.work.tdd.euler.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 14/09/12
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class PermutePrinter {

    List<String> permutes = new ArrayList<>();

    public <T> void permute(T[] array, int n) {
        if(n == 1) {
            permutes.add(Arrays.toString(array));
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
        PermutePrinter p = new PermutePrinter();
        p.permute(new Integer[]{0, 1, 2, 3, 4}, 5);
        Collections.sort(p.permutes);
//        for (String permute : p.permutes) {
//            System.out.println(permute);
//        }
        System.out.println(Utility.permutes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}
