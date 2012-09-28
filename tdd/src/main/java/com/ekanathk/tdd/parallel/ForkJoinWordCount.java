package com.ekanathk.tdd.parallel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.readAllLines;

/**
 * Created with IntelliJ IDEA.
 * User: EkanathK
 * Date: 28/09/12
 */
class WordCountTask extends RecursiveTask<Map<String, Long>>{

    private Path filePath;
    private static final Logger logger = Logger.getLogger(WordCountTask.class.getName());

    WordCountTask(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Map<String, Long> compute() {
        try {
            List<String> strings = readAllLines(filePath, Charset.defaultCharset());
            return countWords(strings);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception occured", e);
            return Collections.emptyMap();
        }
    }

    private Map<String, Long> countWords(List<String> lines) {
        Map<String, Long> wordCount = new HashMap<>();
        for(String line : lines) {
            StringTokenizer tk = new StringTokenizer(line);
            while(tk.hasMoreTokens()) {
                String token = tk.nextToken();
                Long count = wordCount.get(token);
                count = count == null? 0 : count;
                wordCount.put(token, count+1);
            }
        }
        logger.info("File [" + filePath + "] has word count [" + wordCount.size() + "]");
        return wordCount;
    }
}

public class ForkJoinWordCount extends RecursiveTask<Map<String, Long>> {

    private String folderName;

    public ForkJoinWordCount(String folderName) {
        this.folderName = folderName;
    }

    public static Map<String, Long> wordCount(String folderName) throws IOException {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return pool.submit(new ForkJoinWordCount(folderName)).join();
    }

    protected void mergeCounts(Map<String, Long> map1, Map<String, Long> map2) {
        for (Map.Entry<String, Long> entry : map2.entrySet()) {
            Long count = map1.get(entry.getKey());
            count = count == null ? 0 : count;
            map1.put(entry.getKey(), count + entry.getValue());
        }
    }

    @Override
    protected Map<String, Long> compute() {
        List<ForkJoinTask<Map<String,Long>>> tasks = new ArrayList<>();
        try (DirectoryStream<Path> ds = newDirectoryStream(getDefault().getPath(folderName))) {
            for (Path p : ds) {
                tasks.add(new WordCountTask(p).fork());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Long> totalCount = new HashMap<>();
        for(ForkJoinTask<Map<String,Long>> t : tasks) {
            mergeCounts(totalCount, t.join());
        }
        return totalCount;
    }
}
