package com.mundo.data.support;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Snowflake
 *
 * @author maodh
 * @see <a href="https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html">Announcing Snowflake</a>
 * @since 2018/7/22
 */
@ThreadSafe
public final class Snowflake {
    private static final int BIT_TIMESTAMP = 41; // 时间戳占用位数
    private static final int BIT_WORKER_NUMBER = 10; // 机器号占用位数
    private static final int BIT_SEQUENCE_NUMBER = 12; // 序列号占用位数

    private static final int LEFT_TIMESTAMP = BIT_SEQUENCE_NUMBER + BIT_WORKER_NUMBER;
    private static final int LEFT_WORKER_NUMBER = BIT_SEQUENCE_NUMBER;

    private static final long MAX_TIMESTAMP = 1L << BIT_TIMESTAMP;
    private static final long MAX_WORKER_NUMBER = 1L << BIT_WORKER_NUMBER;
    private static final long MAX_SEQUENCE_NUMBER = 1L << BIT_SEQUENCE_NUMBER;

    private static final long START_TIMESTAMP = 1546272000000L; // Tue Jan 01 2019 00:00:00 GMT+0800 (China Standard Time)
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private final long workerNumber;
    private volatile long lastTimestamp;

    private Snowflake() {
        throw new AssertionError("No Snowflake instances for you!");
    }

    private Snowflake(long workerNumber) {
        this.workerNumber = workerNumber;
        this.lastTimestamp = 0L;
    }

    public static Snowflake generateId(long workerNumber) {
        if (workerNumber < 0 || workerNumber >= MAX_WORKER_NUMBER) {
            throw new IllegalArgumentException();
        }
        return new Snowflake(workerNumber);
    }

    public synchronized long nextId() {
        final long workerNumber = this.workerNumber;

        long currentTimestamp = System.currentTimeMillis();
        final long thisTimestamp = currentTimestamp - START_TIMESTAMP;

        if (thisTimestamp > this.lastTimestamp && this.lastTimestamp != 0L) {
            Snowflake.atomicInteger.set(1);
        }
        final long sequenceNumber = Snowflake.atomicInteger.getAndIncrement();

        try {
            return thisTimestamp << LEFT_TIMESTAMP | workerNumber << LEFT_WORKER_NUMBER | sequenceNumber;
        } finally {
            this.lastTimestamp = thisTimestamp;
        }
    }
}