package com.mundo.data.support;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Snowflake
 *
 * @author maodh
 * @see <a href="https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html">Announcing Snowflake</a>
 * @since 2018/7/22
 */
public interface Snowflake {

    long nextId();

    @ThreadSafe
    class TwitterSnowflake implements Snowflake {
        private static final int BIT_NOT_USED = 1; // 尚未使用
        private static final int BIT_TIMESTAMP = 41; // 时间戳占用位数
        private static final int BIT_WORKER_NUMBER = 7; // 机器号占用位数
        private static final int BIT_SEQUENCE_NUMBER = 15; // 序列号占用位数

        private static final int LEFT_SEQUENCE_NUMBER = 0; // 左偏移量：序列号
        private static final int LEFT_WORKER_NUMBER = LEFT_SEQUENCE_NUMBER + BIT_SEQUENCE_NUMBER; // 左偏移量：机器号
        private static final int LEFT_TIMESTAMP = LEFT_WORKER_NUMBER + BIT_WORKER_NUMBER; // 左偏移量：时间戳

        private static final int RIGHT_TIMESTAMP = BIT_NOT_USED; // 右偏移量：时间戳
        private static final int RIGHT_WORKER_NUMBER = RIGHT_TIMESTAMP + BIT_TIMESTAMP; // 右偏移量：机器号
        private static final int RIGHT_SEQUENCE_NUMBER = RIGHT_WORKER_NUMBER + BIT_WORKER_NUMBER; // 右偏移量：序列号

        private static final long MAX_TIMESTAMP = 1L << BIT_TIMESTAMP; // 最大值：时间戳
        private static final long MAX_WORKER_NUMBER = 1L << BIT_WORKER_NUMBER; // 最大值：机器号
        private static final long MAX_SEQUENCE_NUMBER = 1L << BIT_SEQUENCE_NUMBER; // 最大值：序列号

        private static final long START_TIMESTAMP = 1546272000000L; // Tue Jan 01 2019 00:00:00 GMT+0800 (China Standard Time)

        private final long workerNumber;
        private long lastTimestamp;
        private long lastSequenceNumber;
        private final Lock lock = new ReentrantLock();

        private TwitterSnowflake(long workerNumber) {
            this.workerNumber = workerNumber;
            this.lastTimestamp = 0L;
            this.lastSequenceNumber = 0L;
        }

        public static Snowflake getInstance(long workerNumber) {
            if (workerNumber < 0 || workerNumber >= MAX_WORKER_NUMBER) {
                throw new IllegalArgumentException();
            }
            return new TwitterSnowflake(workerNumber);
        }

        public static String toBinaryString(long id) {
            final char[] chars = new char[64];
            Arrays.fill(chars, '0');
            String idBinaryString = Long.toBinaryString(id);
            idBinaryString.getChars(0, idBinaryString.length(), chars, 64 - idBinaryString.length());

            final String binaryString = new String(chars);
            return String.format("raw id: %s", id) + System.lineSeparator() +
                    String.format("binary string: %s", binaryString) + System.lineSeparator() +
                    String.format("segment string: %s, %s, %s, %s",
                            binaryString.substring(0, TwitterSnowflake.RIGHT_TIMESTAMP),
                            binaryString.substring(TwitterSnowflake.RIGHT_TIMESTAMP, TwitterSnowflake.RIGHT_WORKER_NUMBER),
                            binaryString.substring(TwitterSnowflake.RIGHT_WORKER_NUMBER, TwitterSnowflake.RIGHT_SEQUENCE_NUMBER),
                            binaryString.substring(TwitterSnowflake.RIGHT_SEQUENCE_NUMBER, 64));
        }

        @Override
        public long nextId() {
            final long workerNumber = this.workerNumber;

            final long thisTimestamp = this.getThisTimestamp();
            if (thisTimestamp >= MAX_TIMESTAMP) {
                throw new IllegalArgumentException();
            }

            final long thisSequenceNumber = this.getSequenceNumber(thisTimestamp);
            if (thisSequenceNumber >= MAX_SEQUENCE_NUMBER) {
                throw new IllegalArgumentException();
            }

            return thisTimestamp << LEFT_TIMESTAMP | workerNumber << LEFT_WORKER_NUMBER | thisSequenceNumber;
        }

        private long getThisTimestamp() {
            long currentTimestamp = System.currentTimeMillis();
            return currentTimestamp - START_TIMESTAMP;
        }

        private long getSequenceNumber(long thisTimestamp) {
            lock.lock();
            try {
                if (thisTimestamp > this.lastTimestamp && this.lastTimestamp != 0L) {
                    this.lastSequenceNumber = 0L;
                }
                this.lastTimestamp = thisTimestamp;
                this.lastSequenceNumber = this.lastSequenceNumber + 1;
                return this.lastSequenceNumber;
            } finally {
                lock.unlock();
            }
        }
    }
}