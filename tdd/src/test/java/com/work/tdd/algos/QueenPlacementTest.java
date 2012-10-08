package com.work.tdd.algos;

import org.junit.Test;

import java.util.List;

import static com.work.tdd.algos.QueenPlacement.placements;
import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: kannan
 * Date: 22/09/12
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class QueenPlacementTest {

    @Test
    public void testSimple() {
        List<List<Integer>> solutions = placements(5);
        assertEquals(10, solutions.size());
    }
}
