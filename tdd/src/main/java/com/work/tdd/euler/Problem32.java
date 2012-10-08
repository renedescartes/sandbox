package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.sortListOfLists;
import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 07/10/12
 */
public class Problem32 {

    private static final Logger logger = Logger.getLogger(Problem32.class.getName());


    private static List<Integer> findApplicableProducts(List<Integer> pandigital) {
        List<Integer> products = new ArrayList<>();
        for(int i = 1; i < 8; i++) {
            for(int j = i + 1; j < 9;j++) {
                Integer op1 = parseInteger(pandigital, 0, i);
                Integer op2 = parseInteger(pandigital, i, j);
                Integer product = parseInteger(pandigital, j, 9);
                logger.fine(op1 + " * " + op2 + " = " + product);
                if(op1 * op2 == product) {
                    products.add(product);
                }
            }
        }
        return products;
    }

    private static Integer parseInteger(List<Integer> list, int i, int j) {
        return Integer.parseInt(StringUtils.join(list.subList(i, j), ""));
    }

    public static long answer(List<Integer> array) {
        List<List<Integer>> permutes = Utility.permutes(array);
        sortListOfLists(permutes);
        Set<Integer> products = new TreeSet<>();
        for (List<Integer> permute : permutes) {
            if(products.addAll(findApplicableProducts(permute))) {
                logger.info("Products are " + products);
            }
        }
        return Utility.summation(products.toArray(new Integer[products.size()]));
    }



    @Test
    public void testSimple() {
        assertEquals(answer(Arrays.asList(1,2,3,4,5,6,7,8,9)), 45228);
        assertEquals(findApplicableProducts(Arrays.asList(3, 9, 1, 8, 6, 7, 2, 5, 4)), Arrays.asList(7254));
    }
}
