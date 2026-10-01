package cn.fantasticmao.mundo.core.support;

/**
 * An immutable pair of two values.
 *
 * @param <T> type of the first value
 * @param <R> type of the second value
 * @param t   the first value
 * @param r   the second value
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-05
 */
public record Pair<T, R>(T t, R r) {
}
