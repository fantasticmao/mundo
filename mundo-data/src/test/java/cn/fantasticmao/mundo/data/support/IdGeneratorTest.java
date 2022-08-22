package cn.fantasticmao.mundo.data.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * SnowflakeTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019/1/2
 */
public class IdGeneratorTest {

    @Test
    public void maxValue() {
        Assertions.assertEquals(22, IdGenerator.Snowflake.LEFT_TIMESTAMP);
        Assertions.assertEquals(12, IdGenerator.Snowflake.LEFT_WORKER_NUMBER);
        Assertions.assertEquals(0, IdGenerator.Snowflake.LEFT_SEQUENCE_NUMBER);

        Assertions.assertEquals(2199023255551L, IdGenerator.Snowflake.MAX_TIMESTAMP);
        Assertions.assertEquals(1023L, IdGenerator.Snowflake.MAX_WORKER_NUMBER);
        Assertions.assertEquals(4095L, IdGenerator.Snowflake.MAX_SEQUENCE_NUMBER);
    }

    @Test
    public void next() {
        final long testTimestamp = 1640966400000L;
        final long testWorkerNumber = 666;
        final long id = new IdGenerator.Snowflake(testWorkerNumber) {
            @Override
            protected long timeGen() {
                return testTimestamp;
            }
        }.next();
        Assertions.assertEquals(testTimestamp, IdGenerator.Snowflake.getTimestamp(id));
        Assertions.assertEquals(testWorkerNumber, IdGenerator.Snowflake.getWorkerNumber(id));
        Assertions.assertEquals(0, IdGenerator.Snowflake.getSequence(id));
    }

    @Test
    public void concurrency() throws InterruptedException {
        IdGenerator idGenerator = IdGenerator.Snowflake.newInstance(1);

        final int poolSize = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        List<Long> idList = Collections.synchronizedList(new ArrayList<>(5_000_000));
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(() -> {
                while (!Thread.interrupted()) {
                    idList.add(idGenerator.next());
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();
        boolean isTerminated = executorService.awaitTermination(1, TimeUnit.SECONDS);
        Assertions.assertTrue(isTerminated);

        List<Long> distinctIdList = idList.stream()
            .distinct()
            .collect(Collectors.toList());
        Assertions.assertEquals(idList.size(), distinctIdList.size());
    }
}
