package com.ekanathk.tdd.permute;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 14/09/12
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class HeapifyTest {

    @Test
    public void testSimple() {
        assertEquals(3, Heapify.level(6));
        assertEquals(2, Heapify.level(1));
        assertEquals(3, Heapify.level(5));
        assertEquals(4, Heapify.level(13));
        assertEquals(4, Heapify.level(14));
        assertEquals(5, Heapify.level(15));
        assertEquals(5, Heapify.level(29));
    }

    @Test
    public void testPrint() {
        Heapify h = new Heapify();
        h.add(60, 40, 90, 35, 33, 47, 49, 25, 30, 24, 22);
        h.printTree(System.out);
    }

    @Test
    public void reheaping() {
        Heapify h = new Heapify();
        h.add(60, 40, 90, 35, 33, 47, 49, 25, 30, 24, 22);
        h.printTree(System.out);
        assertEquals(90, h.takeHeadAndRebalance().intValue());
        h.printTree(System.out);
    }
}
