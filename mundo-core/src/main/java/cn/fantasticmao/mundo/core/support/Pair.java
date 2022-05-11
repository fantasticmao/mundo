package cn.fantasticmao.mundo.core.support;

import lombok.Getter;
import lombok.Setter;

/**
 * Pair
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
@Getter
@Setter
public final class Pair<T, R> {
    private T t;
    private R r;

    public Pair() {
    }

    public Pair(T t, R r) {
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "Pair{" +
            "t=" + t +
            ", r=" + r +
            '}';
    }
}
