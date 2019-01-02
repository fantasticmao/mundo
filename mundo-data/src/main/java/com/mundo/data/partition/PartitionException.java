package com.mundo.data.partition;

/**
 * PartitionException
 *
 * @author maodh
 * @since 2017/11/17
 */
public class PartitionException extends RuntimeException {

    public PartitionException(String message) {
        super(message);
    }

    public PartitionException(Throwable cause) {
        super(cause);
    }

    public PartitionException(String message, Throwable cause) {
        super(message, cause);
    }
}