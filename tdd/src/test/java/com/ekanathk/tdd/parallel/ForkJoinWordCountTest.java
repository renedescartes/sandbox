package com.ekanathk.tdd.parallel;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: EkanathK
 * Date: 28/09/12
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public class ForkJoinWordCountTest {

    private static final Logger logger = Logger.getLogger(ForkJoinWordCountTest.class.getName());

    @Test
    public void testSimple() throws IOException {
        Map<String,Long> counts = ForkJoinWordCount.wordCount("C:\\Share");
        logger.info("Counts" + counts);
        assertTrue(counts.size() > 0);
    }
}
