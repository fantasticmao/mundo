package cn.fantasticmao.mundo.data.support;

/**
 * Distributed id generator.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-07-22
 */
public interface IdGenerator {

    long next();

    /**
     * Twitter Snowflake
     *
     * @see <a href="https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html">Announcing Snowflake</a>
     * @see <a href="https://github.com/twitter-archive/snowflake/blob/b3f6a3c6ca8e1b6847baa6ff42bf72201e2c2231/src/main/scala/com/twitter/service/snowflake/IdWorker.scala">twitter-archive snowflake</a>
     */
    class Snowflake implements IdGenerator {
        private static final int BIT_TIMESTAMP = 41; // bit: timestamp
        private static final int BIT_WORKER_NUMBER = 10; // bit: worker number
        private static final int BIT_SEQUENCE_NUMBER = 12; // bit: sequence number

        protected static final int LEFT_SEQUENCE_NUMBER = 0; // left offset: sequence number
        protected static final int LEFT_WORKER_NUMBER = LEFT_SEQUENCE_NUMBER + BIT_SEQUENCE_NUMBER; // left offset: worker number
        protected static final int LEFT_TIMESTAMP = LEFT_WORKER_NUMBER + BIT_WORKER_NUMBER; // left offset: timestamp

        protected static final long MAX_TIMESTAMP = (1L << BIT_TIMESTAMP) - 1; // mav value: timestamp
        protected static final long MAX_WORKER_NUMBER = (1L << BIT_WORKER_NUMBER) - 1; // mav value: worker number
        protected static final long MAX_SEQUENCE_NUMBER = (1L << BIT_SEQUENCE_NUMBER) - 1; // mav value: sequence number

        private static final long START_TIMESTAMP = 1577808000000L; // 2020-01-01 00:00:00.000

        private final long workerNumber;
        private long lastTimestamp;
        private long sequence;

        protected Snowflake(long workerNumber) {
            this.workerNumber = workerNumber;
            this.lastTimestamp = 0L;
            this.sequence = 0L;
        }

        public static IdGenerator newInstance(long workerNumber) {
            if (workerNumber < 0 || workerNumber > MAX_WORKER_NUMBER) {
                throw new IllegalArgumentException("Invalid worker number: " + workerNumber);
            }
            return new Snowflake(workerNumber);
        }

        protected static long getTimestamp(final long id) {
            return ((id & (MAX_TIMESTAMP << LEFT_TIMESTAMP)) >>> LEFT_TIMESTAMP) + START_TIMESTAMP;
        }

        protected static long getWorkerNumber(final long id) {
            return (id & (MAX_WORKER_NUMBER << LEFT_WORKER_NUMBER)) >>> LEFT_WORKER_NUMBER;
        }

        protected static long getSequence(final long id) {
            return id & MAX_SEQUENCE_NUMBER;
        }

        @Override
        public synchronized long next() {
            long timestamp = this.timeGen();
            if (timestamp < lastTimestamp) {
                String message = String.format("Clock moved backwards. " +
                    "Refusing to generate id for %d milliseconds", lastTimestamp - timestamp);
                throw new IllegalArgumentException(message);
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & MAX_SEQUENCE_NUMBER;
                if (sequence == 0) {
                    timestamp = this.tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = timestamp;

            return (timestamp - START_TIMESTAMP) << LEFT_TIMESTAMP
                | workerNumber << LEFT_WORKER_NUMBER
                | sequence << LEFT_SEQUENCE_NUMBER;
        }

        protected long timeGen() {
            return System.currentTimeMillis();
        }

        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }
    }
}
