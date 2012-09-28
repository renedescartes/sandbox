package com.ekanathk.tdd.algos;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class RootTest {

    @Test
    public void testSimple() {
       assertEquals(8D, Root.squareRoot(64), Root.ERROR);
        assertEquals(0, Root.squareRoot(0), Root.ERROR);
        assertEquals(8D, Root.squareRoot(2.3345), Root.ERROR);
        assertEquals(8D, Root.squareRoot(63), Root.ERROR);
        assertEquals(1, Root.squareRoot(0.99), Root.ERROR);


    }
}
