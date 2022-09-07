package cn.fantasticmao.mundo.core.support;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Pair
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-05
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(t, pair.t) && Objects.equals(r, pair.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, r);
    }

    @Override
    public String toString() {
        return "Pair{" +
            "t=" + t +
            ", r=" + r +
            "}";
    }
}
