package com.mundo.data.support;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * SnowflakeTest
 *
 * @author maodh
 * @since 2019/1/2
 */
public class SnowflakeTest {

    @Test
    public void test() {
        Snowflake snowflake = Snowflake.TwitterSnowflake.getInstance(1);
        long id = snowflake.nextId();
        System.out.println(Snowflake.TwitterSnowflake.toBinaryString(id));
    }

    @Test
    public void testIfWork() throws IOException, InterruptedException {
        Snowflake snowflake = Snowflake.TwitterSnowflake.getInstance(1);

        CountDownLatch count = new CountDownLatch(1);
        List<Long> idList = Collections.synchronizedList(new ArrayList<>());
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                idList.add(snowflake.nextId());
            }
            count.countDown();
        });
        thread.start();

        TimeUnit.MILLISECONDS.sleep(50);
        thread.interrupt();
        count.await();

        List<String> idStrList = idList.stream().map(String::valueOf).collect(Collectors.toList());

        Path path = Paths.get(Object.class.getResource("/").getPath(), "snowflake.log");
        Files.write(path, idStrList, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    @Test
    public void testConcurrency() throws InterruptedException, ExecutionException, IOException {
        Snowflake snowflake = Snowflake.TwitterSnowflake.getInstance(1);

        final int poolSize = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CompletionService<List<Long>> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < poolSize; i++) {
            completionService.submit(() -> {
                List<Long> idList = Collections.synchronizedList(new ArrayList<>());
                while (!Thread.interrupted()) {
                    idList.add(snowflake.nextId());
                }
                return idList;
            });
        }

        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();

        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            Future<List<Long>> future = completionService.take();
            idList.addAll(future.get());
        }

        List<Long> distinctIdList = idList.stream().distinct().collect(Collectors.toList());
        Assert.assertEquals(idList.size(), distinctIdList.size());

        //List<String> idStrList = idList.stream().map(String::valueOf).collect(Collectors.toList());
        //Path path = Paths.get(Object.class.getResource("/").getPath(), "snowflake.log");
        //Files.write(path, idStrList, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}