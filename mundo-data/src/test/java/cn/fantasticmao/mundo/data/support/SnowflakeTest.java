package cn.fantasticmao.mundo.data.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * SnowflakeTest
 *
 * @author fantasticmao
 * @version 1.0
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

        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();

        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            Future<List<Long>> future = completionService.take();
            idList.addAll(future.get());
        }

        List<Long> distinctIdList = idList.stream().distinct().collect(Collectors.toList());
        Assertions.assertEquals(idList.size(), distinctIdList.size());
    }
}