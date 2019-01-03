package com.mundo.data.support;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * SnowflakeTest
 *
 * @author maodh
 * @since 2019/1/2
 */
public class SnowflakeTest {

    @Test
    public void testDataComposition() {
        Snowflake snowflake = Snowflake.generateId(1);
        long id = snowflake.nextId();
        char[] chars = new char[64];
        Arrays.fill(chars, '0');
        String binaryStr = Long.toBinaryString(id);
        binaryStr.getChars(0, binaryStr.length(), chars, 64 - binaryStr.length());
        binaryStr = new String(chars);

        System.out.println("binary string: " + binaryStr);
        System.out.println("0: " + binaryStr.substring(0, 1));
        System.out.println("41: " + binaryStr.substring(1, 42));
        System.out.println("10: " + binaryStr.substring(42, 52));
        System.out.println("12: " + binaryStr.substring(52, 64));
    }

    @Test
    public void testIfWork() throws IOException, InterruptedException {
        Snowflake snowflake = Snowflake.generateId(1);

        List<Long> idList = new ArrayList<>();
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                idList.add(snowflake.nextId());
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

        List<String> idStrList = idList.stream().map(String::valueOf).collect(Collectors.toList());

        Path path = Paths.get(Object.class.getResource("/").getPath(), "snowflake.log");
        Files.write(path, idStrList, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    @Test
    public void testConcurrency() throws InterruptedException, IOException {
        Snowflake snowflake = Snowflake.generateId(1);
        List<Long> idList = new CopyOnWriteArrayList<>();

        final int poolSize = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.submit(() -> {
                while (!Thread.interrupted()) {
                    idList.add(snowflake.nextId());
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();

        Assert.assertEquals(idList.size(), idList.stream().distinct().collect(Collectors.toList()).size());

        List<String> idStrList = idList.stream().map(String::valueOf).collect(Collectors.toList());
        Path path = Paths.get(Object.class.getResource("/").getPath(), "snowflake.log");
        Files.write(path, idStrList, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}