package com.mundo.data.support;

/**
 * Snowflake
 *
 * @author maodh
 * @see <a href="https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html">Announcing Snowflake</a>
 * @since 2018/7/22
 */
public final class Snowflake {
    private static final int BIT_TIMESTAMP = 41; // 时间戳占用位数
    private static final int BIT_SEQUENCE_NUMBER = 12; // 序列号占用位数
    private static final int BIT_WORKER_NUMBER = 10; // 机器号占用位数

    private static final long MAX_TIMESTAMP = 1L << BIT_TIMESTAMP;
    private static final long MAX_SEQUENCE_NUMBER = 1L << BIT_SEQUENCE_NUMBER;
    private static final long MAX_WORKER_NUMBER = 1L << BIT_WORKER_NUMBER;

    private final long workerNumber;

    private Snowflake() {
        throw new AssertionError("No Snowflake instances for you!");
    }

    private Snowflake(long workerNumber) {
        this.workerNumber = workerNumber;
    }

    public static Snowflake generateId(long workerNumber) {
        return new Snowflake(workerNumber);
    }
}